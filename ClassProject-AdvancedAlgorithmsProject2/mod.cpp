#include <iostream>
#include <fstream>
#include <string>
#include <random>
#include <chrono>
#include "mod.h"

using namespace std;

int main()
{
    int numRand = 0;
    int n = 0;
    int a = 0;
    int temp = 0;
    int b = 0;
    int iterate = 0;
    node * headN = NULL;
    node * currN = NULL;
    headN = new node;
    currN = headN;

    cout<< "How many random integers should be generated?"<< endl;
    cin >> numRand;
    cout<< "What is Modulus?" << endl;
    cin >> n;

    int arr[numRand];

    unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
        std::default_random_engine generator(seed);

        for(int i = 0; i < numRand; i++)
        {
            std::uniform_int_distribution<int> distributiona(1,100);
            a = distributiona(generator);
            arr[i] = a;
        }
        cout << "Random Integers: " << endl;
        for(int i = 0; i < numRand; i++)
        {
            cout<< arr[i] << endl;
        }
        cout<< '\n' << "Sums: ";
        for (int i = 0; i < n; i++)
        {
            for(int j = 0; j < numRand; j++)
            {
                if((arr[j]%n) == i)
                {
                    //cout<< "a";
                    currN->addToList(arr[j]);
                    iterate++;
                }
            }
            currN->setListLength(iterate);
            iterate = 0;
            currN->setId(i);
            currN->makeNew();
            currN = currN->getNext();
        }

        currN = headN;

        //cout<< currN->getId() << " " << currN->getListLength() << endl;

        for(int i = 0; i < n; i++)
        {
            cout<< '\n' << "List " << i << ": ";
            for (int j = 0; j < currN->getListLength(); j++)
            {
                for (int k = 1; k < currN->getListLength(); k++)
                {
                    if (j+k < currN->getListLength())
                    {
                        b = (currN->getListValues(j) + currN->getListValues(j+k));
                        cout<< b << ", ";
                    }

                }

            }
            currN = currN->getNext();
        }



}
