#ifndef MOD_H_INCLUDED
#define MOD_H_INCLUDED

#include <iostream>
#include <fstream>
#include <list>
using namespace std;

class node
{
public:
    node()
    {
        int id = 0;
        int listLength = 0;
    }
    void setId(int i)
    {
        id = i;
    }
    int getId()
    {
        return id;
    }
    node* getNext()
    {
        return next;
    }
    void makeNew()
    {
        next = new node;
    }
    void addToList(int i)
    {
        listN.push_back(i);
    }
    void setListLength(int length)
    {
        listLength = length;
    }
    int getListValues(int i)
    {
        int it = 0;
        std::list<int>::const_iterator iterator;
        for (iterator = listN.begin(); iterator != listN.end(); ++iterator)
        {

            if(i == it)
            {
                return *iterator;
            }
            it++;
        }
    }
    int getListLength()
    {
        return listLength;
    }
private:
    int listLength;
    int id;
    node * next;
    std::list<int> listN;
};

#endif // MOD_H_INCLUDED
