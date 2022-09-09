#include<bits/stdc++.h>

using namespace std;

//============================= implement stack using singly linked list =============================================
template<class T>
class node
{
public:
    node() // default constructor
    {
        next=0;
    }
    node(T el, node *n=0) // parameterized constructor
    {
        data=el;
        next=n;
    }
    T data;
    node *next;
};

template<class T>
class TStackList
{
public:
    node<T> *top;
    TStackList()  // default constructor
    {
        top = 0;
    }
    bool isempty()  // to see if the list is empty or not
    {
       return top == 0;
    }
    void print()  // iterates over all of the elements
    {
        node<T> *temp;
        for(temp=top; temp!= 0; temp=temp->next)
        {
            cout <<"->"<< temp->data;
        }
        cout << endl;

    }
    void push(T el)  // push at the head
    {
       top = new node<T>(el,top);
    }
    void pop() // pop at head
    {
       // if empty
       if(isempty()== true)
       {
           cout <<"stack is empty..."<< endl;
           return;
       }
       // otherwise
       node<T> *temp= top;
       top=top->next;
       delete temp;
    }
    T peek()
    {
        if(isempty()== true)
        {
            cout << "Stack is empty.."<< endl;
            return -1;
        }
        else
            return top->data;
    }


};
//==================== reverse sting
string reverse_string( string input)
{
    // put sting into a stack
   TStackList<char> string_reverse; //initialize my stack as char, to store each character in a separate entity
    for(int i=0; i<input.length(); i++) //loop to store the letters in the stack
    {
        string_reverse.push(input[i]);
    }
    for(int i=0; i<input.length(); i++) // loop to return the letters from stack to the string again
    {
        input[i]=string_reverse.peek();
        string_reverse.pop();
    }
    return input;
}
// ==================== palindrome question
bool ispalindrome(string word)
{
    TStackList<char> first_part;  // First part of the string
    TStackList<char> second_part;  // second part of string
    int half = word.length()/2; // to know where to start
    for(int i=0; i<half; i++)  // put the string in a stack from index 0 until index half
    {
        if(word[i] != ' ') // we ignore spaces
            first_part.push(tolower(word[i])); // we must have all same case (to be case insensitive )
    }
    if (word.length()%2 != 0) // change the stop of next if it was an odd word
        half++;

    for(int i=word.length()-1; i>=half; i--) // put string in a stack from right to left
    {
        if(word[i] != ' ')
            second_part.push(tolower(word[i]));
    }

    while(first_part.isempty()== false)  // loop until the stack finish, no need to make sure that stack two is also empty, as both half same size and we pop in both simultaneously
    {
        if(first_part.peek()== second_part.peek()) // same top ?
        {
            first_part.pop();
            second_part.pop();  // we pop in both to continue comparing
        }
        else
        {
            return false;  // the top is not the same, which means this is not a palindrome, thus no need to continue searching, and will just return false
        }
    }
    return true; // will only reach here if both stacks are the same , meaning it is a palindrome
}

//======================= implement stack using queue============

//=========== queue implementation
template <class T>
class queue_linked
{
public:
    node<T> *head; // we need head and tail, because we will manipulate from two sides
    node<T> *tail;
    queue_linked()  // default constructor
    {
        head= tail =0;
    }
    void print()  // traverse normally, as we already add elements in the correct order
    {
        node<T> *temp;
        for(temp= head; temp!=0; temp=temp->next)
        {
            cout <<"->"<<temp->data;
        }
        cout << endl;
    }
    bool isempty()
    {
        return head==0;
    }
    void enqueue(T el) // we add at the bottom
    {
        // if not empty
        if(tail !=0)
        {
            node<T> *newel = new node<T>(el);
            tail->next= newel;
            tail=tail->next;
        }
        else
        {
            head = tail = new node<T>(el);
        }

    }
    void dequeue() // we will delete from the top / head
    {
       //if empty stops
       if(isempty()== true)
       {
           cout <<"Queue is empty..."<< endl;
           return;
       }
       node<T> *temp = head;
       head= head->next;
       delete temp;

    }
    int size_()  // traverse and keep a running counter
    {
       node<T> *temp;
       int counter =0;
       for(temp= head; temp!=0; temp=temp->next)
       {
           counter ++;
       }
       return counter;
    }
    T first_el()  // return the first element
    {
        if(isempty()== true)
        {
            cout << "Already empty.."<< endl;
            return -1;
        }
        else
        {
            return head->data;
        }
    }


};
//========== stack using queue
template<class T>
class Stack_using_queue
{
public:
    queue_linked<T> q; // this is to store the stack in a form of queue
    bool isempty()
    {
       return q.isempty();  // we use the queue member functions
    }
    void print()
    {
       q.print();

    }
    void push(T el)
    {
        queue_linked<T> temp;  // a temp queue to store the element we want to be in the top
       temp.enqueue(el);
       while( q.isempty()== false) // we add the rest of the elements to temp
       {
           temp.enqueue(q.first_el());
           q.dequeue();
       }
       swap(q,temp); // make q temp

    }
    void pop()
    {
       q.dequeue();  // both stack and queue delete from the top

    }
    T peek()
    {
        return q.first_el();  // uses the queue q.first
    }


};


int main()
{
cout<<"This is just trying out the functions, to check compare code with the printed statements"<< endl;
cout <<"Question 1========================"<<endl;
   TStackList<int> x;
   x.push(5);
   x.push(7);
   x.push(8);
   x.print();
   cout << x.peek()<< endl;
   x.pop();
   x.print();
   x.pop();
   x.print();
   x.pop();
   x.print();
   x.pop();
   x.print();
cout <<"Question 2========================"<<endl;
 cout << reverse_string("ABCD")<< endl;
cout <<"Question 3========================"<<endl;
cout << ispalindrome("AZIZA")<< endl;
cout << ispalindrome("AZIIZA")<< endl;
cout<< ispalindrome("Murder for a jar of red rum")<< endl;
cout <<"Question 4========================"<<endl;
 Stack_using_queue<int> xx;
   xx.push(5);
   xx.push(7);
   xx.push(8);
   xx.push(9);
   xx.print();
   cout << xx.peek()<< endl;
   xx.pop();
   xx.print();
   xx.pop();
   xx.print();
   xx.pop();
   xx.print();
   xx.pop();
   xx.print();
cout<<"Have a beautiful day like you Eng. Asmaa <3"<< endl;
    return 0;
}
