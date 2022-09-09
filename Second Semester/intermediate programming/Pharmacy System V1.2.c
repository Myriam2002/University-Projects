//all the libraries needed
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

#define SIZE 30 // constant, max size of any word in our file

//Global variables
static FILE* fptr;  //the file pointer
char FormedArray[255][SIZE]; // an array of strings ["word1","word2"...etc]
char userInput;
int WordCount=0;//to be able to refer to them throughout
char userInputString[SIZE];// used in any user input
//==================================prototypes=====================================================================================================
//---------extra functions
char* strlower(char *string);
int is_digit_quantity(char *string);
int is_digit_price(char *string);
int is_date_format(char *s);
void commands();
int Find_ID(char ID[]);
void updateCSV();
void FormArray();
void commandsInterface();
int FileExists(char* fname);
int doesExist(char* id);
//---------project functions
void AddMedicine();
void DeleteMedicine(char* id);
int UpdateMedicine(char ID[]);
void PrintMedicinesInStock();
void GetMedicineInfo(char* id);
void SellMedicine(char* id);
void Quit();

//===================================main==========================================================================================================

int main(void)
{
    //opening the file
	if (FileExists("medicine.csv"))// file is there, so will append on it
	{
		fptr = fopen("medicine.csv", "a"); //a to append on text to the CSV file
	}
	else //file is not there so will create and add the headers -> so can be representable in the csv file
	{
		fptr = fopen("medicine.csv", "w");
		fprintf(fptr, "ID,Name,Company,M-Date,EXP,Quantity,Price\n");
		fclose(fptr);
	}

    commandsInterface(); //gives all options
    FormArray();//makes initial array of all what is in file
    commands(); //all the project options to select from
}
//==========================================================FUNCTIONS==========================================================
//--Project Functions--
void AddMedicine()
{
    //open file first in append mode
    fptr = fopen("medicine.csv", "a");
	char id[SIZE], name[SIZE], company[SIZE], manu[SIZE], expiry[SIZE], quantity[SIZE], price[SIZE]; //initialize the variables that user will input

    //validating that the medicine is not already there
    printf("Enter Medicine ID:\n");
    scanf("%s", &id);
    fflush(stdin);
    if(doesExist(id)){
        printf("This ID already exists.\n");
        return;
    }

    printf("\nEnter Medicine Name:\n");
    scanf("%s", &name);
    fflush(stdin);
    if(doesExist(name)){
        printf("This name already exists.\n");
        return;
    }

    //user prompted to type each info of the medicine sequentially AND make sure that it is validated to some degree
    printf("\nEnter Company Name:\n");
    scanf("%s", &company);
     fflush(stdin);

     do
        {
        printf("\nEnter Manufacturing Date (e.g. 00-00-0000):\n");
        scanf("%s", &manu);
        fflush(stdin);
        }while(is_date_format(manu));


    do
    {
        printf("\nEnter Expiry Date (e.g. 00-00-0000):\n");
        scanf("%s", &expiry);
        fflush(stdin);
    }while(is_date_format(expiry));


    do
    {
      printf("\nEnter Quantity (must be integer and positive):\n");
    scanf("%s", &quantity);
    fflush(stdin);
    }while(is_digit_quantity(quantity));


    do
    {
        printf("\nEnter Price (e.g. 12.9 or 234):\n");
        scanf("%s", &price);
        fflush(stdin);

    }while(is_digit_price(price));


    //the information is stored in file and separated by commas and newlines to be able to open it directly in a csv file

    fprintf(fptr, "%s,%s,%s,%s,%s,%s,%s\n", id, name, company, manu, expiry, quantity, price); // writing into the file

    printf("\nMedicine with ID %s was added successfully\n", id);
    fclose(fptr);
    //used to update the array that we use to extract info from
    FormArray();
}

void DeleteMedicine(char* id)
{
    //make sure that the input is there to delete
    //doesExist is a function that make sure what you typed is available and if so changes it to be case insensitive
    if(!doesExist(id)){
        printf("No medicine with the provided info.\n");
        return;
    }

    //loop to find the needed data
    for(int i=0;*FormedArray[i]!=*"\0";i++)
    {
        if(!strcmp(FormedArray[i],id))  //compare the id in array and input
        {

            if(i%7==1){i--;} //to check whether it's the id or name
            for(int j=i;j<=i+6;j++)
            {
                strcpy(FormedArray[j],"NA"); //to be able to extract in the function updateCSV() and remove from the file
            }

            break;
        }
    }
    updateCSV(); //to update the file and remove the deleted part
    printf("\nMedicine with ID %s was deleted successfully\n", id);
}

int UpdateMedicine(char ID[])
{
    // Find_ID() Checks through the ID's for a match
    int store_id = Find_ID(ID);  //It stores the position of the found ID or if not found will store -1
    if(store_id == -1)
    {
       printf("This ID is not found.\n");
        return -1;
    }

    // declaring all the variables that the user will change
    //notice that we made the size of the string constant all around the file
    char choice;
    int choice2;
    char new_quantity[SIZE];
    char new_price[SIZE];
    char new_name[SIZE];
    char new_company[SIZE];
    char new_manuf[SIZE];
    char new_expiry[SIZE];
    int found= 0; // used as a flag, so that if user entered anything but 1-6 will exit the the do...while loop
    do
    {
        printf("Enter\n");
        printf("1) Change Quantity.\n");
        printf("2) Change Price.\n");
        printf("3) Change Name.\n");
        printf("4) Change Company.\n");
        printf("5) Change Manufacturing Date.\n");
        printf("6) Change Expiry Date.\n");
        printf("Enter any other number to exit.\n");
        scanf("%c",&choice);
        fflush(stdin);
        switch (choice)
        {
            case '1':
                do
                {
                printf("\nEnter Quantity to be changed (must be a positive integer):\n");
                fflush(stdin);
                scanf("%s",&new_quantity);
                fflush(stdin);
                }while(is_digit_quantity(new_quantity));

                strcpy(FormedArray[store_id+5],new_quantity);
                printf("Quantity has been changed successfully.\n");
                break;
            case '2':
                do
                {
                printf("\nEnter Price to be changed:\n");
                 fflush(stdin);
                 scanf("%s",&new_price);
                 fflush(stdin);

                }while(is_digit_price(new_price));

                 strcpy(FormedArray[store_id+6],new_price);
                 printf("Price has been changed successfully.\n");
                break;
            case '3':
                 printf("\nEnter Name to be changed:\n");
                 fflush(stdin);
                 scanf("%s",new_name);
                 fflush(stdin);
                 if(doesExist(new_name)){   // THE NAMES ARE UNIQUE, So must check that user didnt enter a name that already exists !
                    printf("This medicine name already exists.\n");
                    return 0;
                    }
                 strcpy(FormedArray[store_id+1],new_name);
                 printf("Name has been changed successfully.\n");
                break;
            case '4':
                 printf("\nEnter Company to be changed:\n");
                 fflush(stdin);
                 scanf("%s",new_company);
                 fflush(stdin);
                 strcpy(FormedArray[store_id+2],new_company);
                 printf("Company has been changed successfully.\n");
                break;
            case '5':
                do
                {
                    printf("\nEnter Manufacturing Date to be changed (e.g. 00-00-0000):\n");
                 fflush(stdin);
                 scanf("%s",new_manuf);
                 fflush(stdin);

                }while(is_date_format(new_manuf));

                 strcpy(FormedArray[store_id+3],new_manuf);
                 printf("Manufacturing Date has been changed successfully.\n");
                break;
            case '6':
                do
                {
                    printf("\nEnter Expiry Date to be changed (e.g. 00-00-0000):\n");
                 fflush(stdin);
                 scanf("%s",new_expiry);
                 fflush(stdin);

                }while(is_date_format(new_expiry));

                 strcpy(FormedArray[store_id+4],new_expiry);
                 printf("Expiry Date has been changed successfully.\n");
                break;
            default:
                found=1;
                break;
            }
            if (found== 1) //the user from the started didnt want something
            {
                choice2=0;
            }
            else  // after update wants to leave ?
            {
            printf("Enter 1 to change other details else any other number.\n");
            scanf("%d",&choice2);
            fflush(stdin);  // used to avoid the flood in buffer
            }


    }while( choice2 == 1);

    //erase all the file and updates with the changed FormedArray
    updateCSV();

    return 0;
}

void PrintMedicinesInStock()
{
    if (WordCount==7) //only headers available so no data in stock
    {
        printf("No Data in stock yet...\n");
        return;
    }
    else
        {
           for(int i=0;i<WordCount;i++)
            {
                if (i ==6)
                    printf("%20s|\n----------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n",FormedArray[i]);

                else if (strcmp(FormedArray[i],"NA")==0 || strcmp(FormedArray[i],"na")==0) // skips the parts of the array that were deleted
                    continue;

                 else if(i%7==6)  //to make sure it prints in a table style
                {
                    printf("%20sLE|\n", FormedArray[i]);    //the %20s  -> is used to print all the cells with the same length, to form a table :)
                }
                else
                {
                    printf("%20s|", FormedArray[i]);
                }
            }
        }
        printf("\nPrinting is completed successfully...\n"); //array can have "NA" and count count be 7 to print no data in stock

}

void GetMedicineInfo(char* id)
{
    // again here it checks if the inputted id or name is available and also handles the case insensitivity required
    if(!doesExist(id)){
        printf("No medicine in stock with this ID or Name.\n");
        return ;
    }

    for(int i=0;*FormedArray[i]!=*"\0";i++)
    {
        if(!strcmp(FormedArray[i],id))
        {

            if(i%7==1){i--;} //to check whether it's the id or name
            printf("\nID: %s"
                   "\nName: %s"
                   "\nCompany: %s"
                   "\nManufacturing Date: %s"
                   "\nExpiry Date: %s"
                   "\nQuantity: %s"
                   "\nPrice: %sLE\n\n",FormedArray[i],FormedArray[i+1],FormedArray[i+2],FormedArray[i+3],FormedArray[i+4],FormedArray[i+5],FormedArray[i+6]);
            break;
        }
    }
}

void SellMedicine(char* id)
{
    //initialize the variables used in the calculations
    int quantityAvailable=0 , quantity=0, quantityIndex=0;
    float price=0.0;
    char quantityString[SIZE];

    //again makes sure the id or name entered is available
    if(!doesExist(id)){
        printf("none\n");
        return ;
    }

    //Exactly same as GetMedicineInfo but with some tweaks
    for(int i=0;*FormedArray[i]!=*"\0";i++)
    {
        if(!strcmp(FormedArray[i],id))
        {
            if(i%7==1){i--;} //to check whether it's the id or name

            quantityIndex=i+5;
            quantityAvailable = atoi(FormedArray[i+5]); //get the value of qunatity as type int to be able to make calculations on it
            price = atof(FormedArray[i+6]); //get the value of price as type float

            printf("-----------------------------------"
                   "\nID: %s"
                   "\nName: %s"
                   "\nCompany: %s"
                   "\nManufacturing Date: %s"
                   "\nExpiry Date: %s"
                   "\nQuantity: %s"
                   "\nPrice: %sLE\n",FormedArray[i],FormedArray[i+1],FormedArray[i+2],FormedArray[i+3],FormedArray[i+4],FormedArray[i+5],FormedArray[i+6]);
                  printf("-----------------------------------\n");
            break;
        }
    }

    printf("\nEnter 1 to buy medicine %s, else enter 0\n",id);
    scanf("%d",&userInput);
    fflush(stdin);
    if(userInput!=1){
        return ;
    }

    printf("Enter the quantity:\n");
    scanf("%d",&quantity);
    fflush(stdin);
    if(0<quantity && quantity<quantityAvailable)
    {
        printf("Total price = %.2f LE\n",(price*quantity)); //print total price to the user

        quantityAvailable=quantityAvailable-quantity; //calculate quantity available
        itoa(quantityAvailable, quantityString, 10); //convert the int value of quantityAvailable to a string and store it in quantityString, base 10
        printf("Process completed successfully!\nAvailable quantity now = %s\n", quantityString);

        strcpy(FormedArray[quantityIndex],quantityString); //change the value in the array
        updateCSV();
    }else
    {
        printf("The quantity you've entered is invalid..\n"); //to clearly state why the process selected will not work
    }
}

void Quit()
{
    updateCSV(); // updates again to make sure all is well and close the file to maintain all the edits done
    printf("Quit");
    return ;
}


//--Extra Functions--
void commands()
{
    //function to display the user interface
    printf("\nPlease enter your function of choice (1-7): ");
    scanf("%c",&userInput);
    fflush(stdin); //to remove the rest if the user added more strings
    printf("\n***********************************************************\n");
    //used switch instead of if as it has direct access
    switch (userInput){    // used char instead of int , as to avoid conflict if user inputted a string
        case '1': //AddMedicine
            AddMedicine();
            commands(); // to form a recursion that stops when the user is done
            break;
        case '2': //DeleteMedicine
            printf("Enter the medicine ID or Name for deletion: ");
            scanf("%s",&userInputString);
            fflush(stdin);
            DeleteMedicine(userInputString);
            commands();
            break;
        case '3': //Update Medicine details
            printf("Enter the medicine ID for updating: ");
            fflush(stdin);
            scanf("%s",userInputString);
            fflush(stdin);
            UpdateMedicine(userInputString);
            commands();
            break;
        case '4': //Print All Medicines in Stock
            PrintMedicinesInStock();
            commands();
            break;
        case '5': //Get Medicine Info
            printf("Enter the medicine ID or Name to print its info: ");
            scanf("%s",&userInputString);
             fflush(stdin);
            GetMedicineInfo(userInputString);
            commands();
            break;
        case '6': //Sell Medicine
            printf("Enter the medicine ID or Name to sell: ");
            scanf("%s",&userInputString);
             fflush(stdin);
            SellMedicine(userInputString);
            commands();
            break;
        case '7': //Quit
            Quit();
            break;
        default:
            printf("none\n"); // when the user entered a number out of the choices
            commands(); //re-prompts the user
    }
}

int Find_ID(char ID[])
{
    //used to search for the ID - using linear search
    int stored_id;
    for(int i=7; i< WordCount; i++ )
    {
        if (i%7==0)
        {
            if ( strcmp( ID ,FormedArray[i])== 0)
            {
                return i;  //returns the index if the ID was found
            }
        }
    }
    return -1; // returns -1 when not found
}

void updateCSV()
{

    fptr = fopen("medicine.csv", "w+"); //opened in w+ , reading and writing mode
    for(int i=0;i<WordCount;i++)
    {
        if (strcmp(FormedArray[i],"NA")==0 || strcmp(FormedArray[i],"na")==0) // to remove any versions of NA or na made by the delete function , so only prints valid data to file
            continue;
        else if(i%7==6) // to maintain the look of the csv file
        {
            fprintf(fptr, "%s\n", FormedArray[i]);
        }
        else
        {
            fprintf(fptr, "%s,", FormedArray[i]);
        }

    }
    fclose(fptr); // close the file to avoid any playing around
}

void FormArray()
{
    //Function that pass by each char in the CSV file, it detects words separated by ',' then store them in our FormedArray.
    fptr = fopen("medicine.csv", "r"); //open file in read mode
    rewind(fptr);
    char word[SIZE], c;

    c = fgetc(fptr);
    WordCount=0;
    while (c!=EOF) //EOF stands for End Of File
    {
        c = fgetc(fptr);
        if(c=='\n')
        {
            WordCount=WordCount+7; //because each row has 7 words
        }
    }
    rewind(fptr); //makes the pointer go back to the start of the file

    int j;
    for(int i=0;i<WordCount;i++) //each iteration we will get a word from the CSV file
    {
        j=0;
        while(1)
        {
            c = fgetc(fptr);
            if(c==',' || c=='\n') //check the end of the word
            {
                for(j;j<SIZE;j++){
                    word[j]='\0'; //fixing a bug here
                }
                break;
            }
            else
            {
                word[j]=c; //append c to the word each j iteration
                j++;
            }
        }
        //printf("%s,",word); debug
        strcpy(FormedArray[i],word); //copy the word we formed and put it into the array FormedArray
    }
    fclose(fptr);
    //return 0;
}

void commandsInterface()
{
    //user interface
    printf("********************Pharmacy System*************************\n"
           "1) Add Medicine.\n"
           "2) Delete Medicine.\n"
           "3) Update Medicine details.\n"
           "4) Print All Medicines in Stock.\n"
           "5) Print info of Certain Medicine.\n"
           "6) Sell Medicine.\n"
           "7) Quit.\n"
           "***********************************************************\n"
           );
}

int FileExists(char* fname)
{
    // checks if file already exists in path
	FILE *file;
	if ((file = fopen(fname, "r")))
	{
		fclose(file);
		return 1;
	}
	return 0;
}

int doesExist(char* id)
{
    //checks if the id or name are repeated in the array
    for(int i=0;i<WordCount;i++){
        if(i%7==0 || i%7==1){
            if(!strcmp(strlower(FormedArray[i]),strlower(id))){ // case insensitive
                return 1;
            }
        }
    }
    return 0;
}
int is_digit_quantity(char *string)
{
    //used to make sure the input of quantity is a number
    for(int i=0; i<strlen(string); i++)
    {
        if (!isdigit(string[i]))
            return -1;
    }
    return 0;
}

int is_digit_price(char *string)
{
    //used to make sure the input of price is a number , accepts it if it is a float like 4.0
    for(int i=0; i<strlen(string); i++)
    {
        if (!isdigit(string[i]) && !(string[i]=='.'))
            return -1;
    }
    return 0;
}

int is_date_format(char *s)
{
    //to maintain the formate -> doesnt check if the date is actually correct or not -> ex: 00-00-0000
    if (strlen(s) != 10)
        return -1;
    for(int i=0; i < 10;i++)
    {
        if(i==0 || i== 1 || i==3 || i==4 || i ==6 || i==7 || i==8 || i==9)
        {
            if(!isdigit(s[i]))
                return -1;
        }
        else
        {
            if (s[i] != '-')
                return -1;
        }

    }
    return 0;
}


char* strlower(char *string)
{
    //used to make the word case insensitive
    char *p=string;
    for(int i=0; i< strlen(p); i++) //loops according to the length of the string
    {
        if(isalpha(p[i])) // only changes the case if the letter is from the alphabetics a-z and A-Z
        {
            p[i]=tolower(p[i]); //uses tolower to change the letter to a small letter and change the string
        }
    }
    return (char *)p; //returns a string
}


