#include <iostream>
#include <string>
#include <algorithm>
using namespace std;
//Mariam Amr Barakat
//202000210


// =====================================miscellaneous Functions==================================
string lower_string(string str)
{
	for(int i=0;str[i]!='\0';i++)
	{
		if (str[i] >= 'A' && str[i] <= 'Z')    //checking for uppercase characters
			str[i] = str[i] + 32;         //converting uppercase to lowercase
	}
	return str;
}
//================================== singly linked list =========================================================================
/*
Note: my code has a slightly different structure from what is required
in Question 1)
    class Snode: contains the data and *next
    Class sll : is the class with the singly linked list functions + *head
    Class SortSingly linked list: this inherits the sll and overrides the "insert()" function
This approach was more logical, compared to having only a node class with the data, next ,head
    1- I will not be able to utilize the virtual function as the class node will not have insert
    2- each node will have a head , which is illogical
    3- I will not be able to use it in question 2
*/
template<class T> // used template so that my list works with any primitive data type
class SNode
{
public:
    // data members
    T data;
    SNode<T> *next;
    // default constructors
    SNode<T>()
    {
        next=0; //initialize the pointer next to zero to point on nothing
    }
    // parameterized constructor
    SNode<T>( T X, SNode<T> *y=0)
    {
        data=X;
        next= y;
    }
};
template<class T> class sll  // this is the class with the head, and the member functions
{
public:
    //data members
    SNode<T> *head;
    // default constructor
    sll()
    {
        head =0;
    }
    // this destructor lead to a heap corruption error 0xc0000374
    /*
    ~sll() // destructor to delete the list as C++ has no garbage collector , this will avoid memory leaks
    {
         SNode<T>* temp;
       while(head != 0)
       {
           temp= head->next;
           delete head;
           head= temp;
       }
       head = 0;

    }
    */
    // --------------------------member functions ----------------------------------
    void traversing() // printing to prove the working of program
    {
        SNode<T> *temp; // must have an additional pointer to loop around
        for(temp=head; temp!= 0; temp=temp->next)
        {
            cout << temp->data <<"->"; //print the data of each iteration
        }
        cout<<"NULL"<< endl; // to confirm we reached the end of the list
    }
    int size_list()
    {
        SNode<T> *temp; // temp pointer to iterate over each element and calculate the running total
        int c=0;
        for(temp = head; temp != 0 ; temp= temp->next, c++);
        return c;
    }
    bool isempty() // makes sure the list is not empty
    {
        return head==0;
    }

    virtual void inserting(T el) // will be virtual so that it overrides when we call the derived class
    {
        //insert at start , as it will be easier to implement
        head = new SNode<T>(el, head);
    }
    void deletion()
    {
      // will delete at the start as easier(required to only use head)
      if(isempty()== true) // is the list empty? must warn, and get out to avoid clashes, can use return -1, however will not work with T = strings
      {
          cout<<"\tAlready empty..\n";
          return;

      }
      if(size_list() == 1)
      {
          head = 0;
      }
      else
      {

          SNode<T> *temp= head; // store the head in a temp, so can delete without losing track of next
          head= head->next; // update the head to next
          delete temp; // delete the temp, which was the first node
          return ; // return the deleted item
      }

    }
    void clear_sll() // deletes the list to be used in the destructor
    {
       SNode<T>* temp;
       while(head != 0)
       {
           temp= head->next;
           delete head;
           head= temp;
       }
       head = 0;
    }
};

template<class T>
class SortSinglyList:public sll<T> // sortedlist inherits from sll, so that we can override insert
{
    /*
    Because the base class depends on a template , its scope is not examined
    1. head and member functions can't be seen in scope
    solution :
    1- for head use , this-> head (this is a reserved word that tells the class that the head implicitly is an address in it  )
    2- when using member functions must say sll<T>::name_of_function()
    */
    public:
        SortSinglyList() // default constructor
        {
            this->head=0;
        }
        ~SortSinglyList()
        {
            sll<T>::clear_sll();
        }

    void inserting( T el)  // supposed to be called TSortedList however,I used "virtual" it must be the same name as the parent class to get inherited
    {

        //N.B. we are ordering in decreasing order
        // we assume that the list is already sorted as we sort as we go

        // do I have no nodes ? then I should just add, and ignore ordering
        if( this->head ==0)
        {
            // just change the head to the address of the new single element
            this->head = new SNode<T>(el, this->head);
            return;
        }
        else // there are already nodes, so I need to find the correct position of insertion
        {

            // we need two pointers, one to store the before and one the after, as the new node will be in between (and no prev to access it )
           SNode<T> *temp1;
           SNode<T> *temp2= this->head;
           T x;// temp storage of data for comparison
           int c=0;  // track the position of insertion(start,middle or end)

           // loop over each node until finds the suitable position
           for(temp1=this->head;temp1 !=0; temp1= temp1->next)
           {

               // stores the data we will compare in x
               x= temp1->data;
               // finds the answer at the start
               if(x <= el && c==0 )
               {
                   // insert at the start of the list
                   SNode<T> *new_element = new SNode<T>(el, this->head);
                   this->head= new_element; // update the head to the new element after fixing the next
                   return; // to stop the function
               }
               if(c >1)
                    temp2= temp2->next; // we need the temp2 to be before temp1, so at start they will have same position then temp1 will be the further node
               // finds the right answer after the start
               if(x<=el)
               {
                   // reached the right spot
                   SNode<T> *new_element= new SNode<T>(el);// create a new node
                   new_element->next= temp1; //new element will point to temp1
                   temp2->next= new_element; // temp2 will point to new
                   // temp2 ----> new_element----> temp1
                   return;
               }

               c++; // to track the positions
           }
            // will only reach here if the el is the smalled, thus will insert at the end
            SNode<T> *new_element = new SNode<T>(el);
            if( sll<T>::size_list() == 1) // if we only have one node, then we need to only do next of temp
            {
                temp2->next=new_element;
                new_element->next=0;
            }
            else  // if more than one then the temp2 is actually behind 2 steps, so need two nexts
            {
                temp2->next->next = new_element;
                new_element->next=0;
            }

        }
        /*if we were able to use *tail
        1. we can insert the smallest number directly at end by comparing last element only
        2. no need to traverse all the list
        */
    }


};

//================================== doubly linked list==========================================================================

/*
Note: My approach is different in the inheritance part
    -Class Dnode : contains the Data, next, prev
    - Class dll : contains the head and tail
    - Class DuplicateManipuationList: contains the function removeDuplicates and inherited dll
I did this approach as it is more intuitive to inherit from the doubly linked list, not the node itself.
The node is a single entity like a cube , and the list is like connected cubes that create a list
so the DuplicateManipulationlist is a list not a single node
*/

template <class T> class DNode // this is the node that contains the data, next and prev pointers
{
public:
    T data;
    DNode<T> *next;
    DNode<T> *prev;
    DNode() { next = prev = 0; } // default constructor
    DNode(const T& el, DNode *n = 0, DNode *p = 0) // parameterized constructor
    {
        data = el; next = n; prev = p;
    }
};
template <class T> class dll
{
public:
    DNode<T> *head;
    DNode<T> *tail;
    DNode<T> *p_at; // used to know where i am in comparison and update the pointer
    dll() { head = tail = p_at = 0;} // default constructor
    ~dll() // to dynamically delete the list as C++ doesn't have a garbage collector
    {
        clear_list();
    }
    void clear_list()
    {
        while(isempty()==false)
        {
           delete_end();

        }
    }
     void delete_end() // utility function
      {
          if(isempty()==true) // there is nothing to delete
            return;
          if(tail == head) // there is only one node
          {
              delete head;
              head= tail = 0;
          }
          else //there are many nodes
          {
           tail = tail ->prev;
           delete tail-> next;
           tail->next=0;
          }

      }
    void traverse () // printing the doubly linked list
    {
        DNode<T> *temp;
        for(temp= head; temp !=0; temp=temp->next) cout << temp->data <<"->";
        cout <<"NULL"<< endl;
    }
    //utility functions
    bool isempty() // checks if the list is empty
    {
        return head == 0;
    }
    int size_list() // contains the size of the doubly linked list
    {
        DNode<T> *temp;
        int c=0;
        for(temp = head; temp != 0 ; temp= temp->next, c++);
        return c;
    }
    void add_at_end(T el)
    {
        //empty?
        if(isempty()== true)
        {
            head = tail = new DNode<T>(el);
        }
        else
        {
            // x -> tail
            tail = new DNode<T>(el,0,tail); //x -> x -> tail
            tail->prev->next= tail; // edits the next of the previous last element
        }
    }

    T delete_at(int position) // deletes at a specific position
    {
        //out of scope position
        if(position >= size_list())
        {
            cout << "out of scope..."<<endl;
            //return -1;
        }
        // is the list empty?
        else if(isempty()== true)
        {
           cout <<"List is already empty..."<<endl;
           //return -1;
        }
        else if(head==tail) // we only have one node in the doubly linked list
        {
            T rt = head ->data; //store the data we will delete so we can return it
            delete head;
            head=tail= p_at=0; // reinitialize all to null to indicate the list is empty
            return rt;
        }
        //delete from start ?
        else if(position == 0)
        {
            T rt = head->data; //to be able to delete the element and yet return it
            head = head->next;  // change the head to the next head
            delete head->prev; // delete the first node
            head->prev=0; // reinitialize the prev of the first element wit null, to indicate no element is before it
            p_at=head;
            return rt;
        }
        // delete from the end ?
        else if(position == size_list()-1)
        {
           T rt = tail->data; // to be able to return the deleted data
           tail = tail->prev; // make the end before the end of the list
           delete tail->next; // delete the last element
           tail->next=0; // reinitialize the next of the last with null, to indicate it is the last element and nothing is after it
           p_at= tail;
           return rt;
        }
        // delete from the middle ?
        else
        {
          // loop until reaches place the node we want to delete
          DNode<T> *looper; // used for looping around the nodes until the position of deletion
          int c=0; // counter to indicate the position of insertion
          for(looper=head;c<position;looper=looper->next, c++);
          T rt = looper->data; // to be able to return
          // x-> to delete -> x
          // x->x
          looper->prev->next= looper->next;
          looper->next->prev= looper->prev;
          p_at=looper->next;
          delete looper;
          return rt;
        }
    }
     DNode<T>* rt_head()  //way to return the head
    {
        return head;
    }

};

template <class T> class  DuplicateManipuationList: public dll<T>
{
public:

    sll<T> removeDuplicates(sll<T> ListToRemove)
    {
        /*
        1- scan the dll and remove anything that is in the sll
        2- return a new list that shows the number of duplicates for each deleted member
        */
        // we can use nested for loop to remove one by one
        SNode<T> *to_remove;  // singly linked list that contains the elements to remove
        int size_of_l= ListToRemove.size_list(); //figures the size of the list
        int arr[size_of_l]={0}; // array that will store the counters if each number
        int indexx=0; //stores where we are in the area index
        for(to_remove= ListToRemove.head; to_remove !=0 ; to_remove=to_remove->next, indexx++)
        {
            // loop through the dll to remove any occurrence of to_remove-> data
            DNode<T> *loop;
            int c=0; // decides on the place to be deleted
            for(loop= dll<T>::rt_head(); loop!=0; c++)
            {
                if(loop->data == to_remove->data) //we found it
                {
                   dll<T>::delete_at(c);  // we delete at this position
                     c--;  //update the c which is the position we are standing at
                     loop= this->p_at;  //updates where we are pointing in the doubly linked list
                    arr[indexx]++;  //we update the counter as we found a new thing we deleted

                }
                else
                    loop= loop->next; //to start comparing next element in the doubly linked list
            }

        }
        sll<int> rt; // the singly linked list that we will return
        for(int i=0; i< size_of_l; i++) // loop to insert the counters to the sll
        {
            rt.inserting(arr[i]);
        }

        return rt; // return the singly linked list


    }
};

//==================================  bonus question  ===========================================================================
template<class T>
class  TSortedArrayList
{
private :
     T *arr;   // this is a pointer to a dynamically allocated array (it is still takes a continuous segment in memory)
    int size_of_arr;  // this keeps track of the actual capacity of the array
    int index_end; // this keeps track of the end of the array were our data stops


public:

    TSortedArrayList()   // default constructor
    {
        size_of_arr=5;  // my array starts with only 5 positions, and doubles if needed
        arr= new T[5];  // dynamically allocating the 5 positions to the area (used this as we initialized it as a pointer at the start)
        index_end= -1;  // the array is still empty, so the index is underflow
    }
    ~TSortedArrayList()  // destructor to delete all the area that is dynamically allocated
    {
        delete [] arr;   // we use this bracket because we are deleting a continuous array in one step
    }
    void copying_data(int x)  // used to reallocate a new contiguous block that is double the older one
    {
        T *new_ = new T[x];  // we dynamically allocate a new block and assign it to new_ (a temp variable)
        for(int i=0; i <size_of_arr; i++)  // we look through the old array and move the data to new_
            new_[i] = move(arr[i]);
        size_of_arr= x; // we update the size of the array
        swap(arr, new_);  // we swap names, to continue using arr variable only through out the class
        delete[] new_;  // deleted the temp array using delete as it was dynamically allocated
    }
   void insert_at_end(T el)  // used to insert the new data at the end
    {
        if(index_end+1 == size_of_arr)  // checks if the positions ran out
        {
            copying_data(2*size_of_arr);  // copies to a larger array and deletes the older (implemented in the above member function)
        }
        arr[++index_end]= el; // assign the position to the new element
    }
    void insertion_sort(T el)  // here we place in order
    {
        insert_at_end(el);                        // 1- add the element at the end
        for (int i = index_end; i >= 0; i--)      // 2- preform bubble sort
        {
            for (int j = index_end; j > index_end - i; j--)
            {
                 if (arr[j] > arr[j - 1])  // if not in descending order
                    swap(arr[j], arr[j-1]);   // swap their positions
            }
        }
    }
    void printlist()  // we print the array
    {
        for(int i=0; i <= index_end; i++) // we stop at index_end as this is our last element
        {
            cout << arr[i] <<" ";
        }
        cout<< endl;
    }

    void deleting_(int position)   // deleting at the position the user chooses
    {
        // out of bound
        if(position > index_end)
        {
            cout << "\tError! Please enter a valid position\n";
        }
        //if position is at end
        if(position ==  index_end)
        {
           index_end--;  // only change were we point
        }
        // if position at start or in the middle
        else
        {
            // relocate all the data and shift backwards, and the deleted item will be push at the end
            /*
            example)   54321  and user wants to remove position 3
                    5 4 3 1 2 and the index_end will point to the one
            */
            for(int i=position+1; i <= index_end; i++)
            {
                swap(arr[i-1], arr[i]);
            }
            index_end--; // change the index_end as we deleted an item
        }
    }
};
//================================= functions for switch ========================================================
void question_1() // user interface
{
    // user choose the type of data
    int x=0;
    cout <<"\t 1. Add new nodes to Sorted list"<<endl;
    cout <<"\t 2. Traverse through the Sorted list"<< endl;
    cout<<"\t 3. Delete the start node"<<endl;
    cout <<"\t Please choose the operation or zero to stop:\t";

    SortSinglyList<string> slist;
    do
    {

        cin >>x;
        switch (x)
        {
            case 1:
                {
                   string n;
                   string nn;
                   int counter;
                   cout<<"\t Please enter the number of elements you want to add:\t";
                   cin >> counter;
                   for(int i=0; i <counter; i++)
                   {
                      cout<<"\t Please enter the element "<< i+1<<"\t";
                      cin >>n;
                      nn= lower_string(n);
                      // change to lower letters to be able to order correctly
                      slist.inserting(nn);

                   }
                    cout <<"\t Please choose the operation or zero to stop \t";
                    break;
                }

            case 2:
                cout <<"\t";
                slist.traversing();
                cout <<"\t Please choose the operation or zero to stop\t";
                break;
            case 3:
                slist.deletion();
                cout <<"\t Please choose the operation or zero to stop\t";
                break;
            case 0:
                break;
            default:
                cout <<"\t Please choose the operation or zero to stop\t";
                break;


        }


    }while(x !=0);
    cout << "\n";
    slist.clear_sll(); // because the destructor gives error
}
void question_2() //user interface
{
    cout <<"\t 1. Add new nodes to doubly linked list"<<endl;
    cout <<"\t 2. Remove duplicates function"<< endl;
    cout<<"\t 3. Print the doubly linked list after removals"<<endl;
    cout <<"\tThis code is for integers, if you want to change to any other data type, change at function question_2()\n";
    cout <<"\t Please choose the operation or zero to stop\t";
    DuplicateManipuationList<int> dl;
    int x=0;
    do
    {
        cin >> x;
        switch(x)
        {
            case 1:
                {
                    cout<<"\tHow many elements do you want to add:\t";
                    int counter_elements;
                    cin>>counter_elements;
                    int xx;
                    for(int i=0; i <counter_elements; i++)
                    {
                    cout <<"\t Please enter element "<<i+1<<" ";
                    cin >> xx;
                    dl.add_at_end(xx);

                    }

                    cout <<"\t Please choose the operation or zero to stop\t";
                    break;
                }

            case 2:
                {
                     cout << "\tElements in doubly linked list:\n\t";
                    dl.traverse();
                    int number;

                    cout <<"\tHow many elements do you want to remove: ";
                    sll<int> to_remove;
                    cin >> number;
                    int input;
                    for(int i=0;i<number ; i++)
                    {
                        cout<< "\telement number "<<i+1<<"\t";
                        cin >> input;
                        to_remove.inserting(input);
                    }

                    sll<int> counter_removed = dl.removeDuplicates(to_remove);
                    cout << "\tcounter of the removed items respectively:\n\t";
                    counter_removed.traversing();
                    counter_removed.clear_sll();
                    cout <<"\t Please choose the operation or zero to stop\t";
                    break;
                }

            case 3:
                cout<<"\t";
                dl.traverse();
                cout <<"\t Please choose the operation or zero to stop\t";
                break;
            case 0:
                break;
            default:
                cout <<"\t Please choose the operation or zero to stop\t";
                break;
        }

    }while(x !=0);
    cout << "\n";
}

void question_3()
{
   // user choose the type of data
    int x=0;
    cout <<"\t 1. Add new element in array"<<endl;
    cout <<"\t 2. Traverse the sorted array"<< endl;
    cout<<"\t 3. Delete an element in array"<<endl;
    cout <<"\t Please choose the operation or zero to stop:\t";

    TSortedArrayList<int>arr1;
    //arr.copying_data(5);
    do
    {

        cin >>x;
        switch (x)
        {
            case 1:
                {
                   int n;
                   int counter;
                   cout<<"\t Please enter the number of elements you need\t";
                    cin>>counter;
                    for(int i=0; i <counter; i++)
                    {
                        cout <<"\t Please enter element "<<i+1<<" ";
                        cin>>n;
                        arr1.insertion_sort(n);
                    }
                    cout <<"\t Please choose the operation or zero to stop \t";
                    break;
                }

            case 2:
                cout <<"\t";
                arr1.printlist();
                cout <<"\t Please choose the operation or zero to stop\t";
                break;
            case 3:
                int p;
                cout<<"\t Please enter the position (zero indexed) that you want to delete\t";
                cin >> p;
                arr1.deleting_(p);
                cout <<"\t Please choose the operation or zero to stop\t";
                break;
            case 0:
                break;
            default:
                cout <<"\t Please choose the operation or zero to stop\t";
                break;


        }


    }while(x !=0);
    cout << "\n";
}

//================================== driver code    ==========================================================
int main()
{
    cout <<"\t\t======= Welcome to Assignment 1 :) =======\n\n";
    cout<< "1)Question 1- Insertion sort in singly linked list"<<endl;
    cout<<"2)Question 2- Remove elements in a doubly linked list"<< endl;
    cout << "3)Bonus Question"<<endl;
    cout<<"Choose the question you want to check (and zero to exit):\t";
    int choice=0;
    cin >> choice;
    while(choice !=0)
    {
        switch (choice)
        {
            case 1:
                question_1();
                 cout<<"Choose the question you want to check (and zero to exit):\t";
                 cin >> choice;
                break;
            case 2:
                question_2();
                 cout<<"Choose the question you want to check (and zero to exit):\t";
                 cin >> choice;
                break;
            case 3:
                question_3();
                 cout<<"Choose the question you want to check (and zero to exit):\t";
                 cin >> choice;
                break;
            default:
                 cout<<"Choose the question you want to check (and zero to exit):\t";
                 cin >> choice;
                 break;
        }
    }
    cout <<"\nHave a beautiful day Eng. Asmaa <3"<<endl;

    return 0;
}
