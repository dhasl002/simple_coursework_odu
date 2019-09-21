#include <iostream>
#include <fstream>
#include <math.h>
#include <list>
#ifndef FLECHETTE_H
#define FLECHETTE_H



class target
{
public:
    target()
    {
        id = 8;
        speed = 10;
        x = -200;
        y = 0;
        profile1 = .77;
        profile2 = 5.5;
        profile3 = 0.0;
        profile4 = 243;
    }
    void moveTarget(double destinationY)
    {
        double times = (sqrt((pow((destinationY-y),2) + (pow(400,2))))/speed);
        //std::cout<<(sqrt(((((int) (destinationY-y))^2)) + ((200 + 200)^2)))/speed;
        y += destinationY/times;
        //std::cout<< destinationY<< " "<< y << "\n";
        x += 400.0/times;
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    double getProfile1()
    {
        return profile1;
    }
    double getProfile2()
    {
        return profile2;
    }
    double getProfile3()
    {
        return profile3;
    }
    double getProfile4()
    {
        return profile4;
    }

private:
    double x;
    double y;
    double speed;
    int id;
    double profile1;
    double profile2;
    double profile3;
    double profile4;


};

class flechette
{
public:
    void setX(int varX)
    {
        x = varX;
    }
    void setY(int varY)
    {
        y = varY;
    }
    flechette* getnext()
    {
        return next;
    }
    void makenew()
    {
        next = new flechette;
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    void addToList(int dustX, int dustY, double sigStr)
    {
        //std::cout<< dustY;
        dustData.push_back(dustX);
        dustData.push_back(dustY);
        dustData.push_back(sigStr);
    }
    void sendToCommand()
    {
        using namespace std;
        ofstream fout;
        fout.open("Command Center.txt");
        int i=0;
        list<double>::const_iterator iterator;
        for( iterator = dustData.begin(); iterator != dustData.end(); ++iterator)
        {
            i++;
            fout<< *iterator << " ";
            if(i%3==0)
            {
                fout<< "\n";
            }
        }
   fout.close();
   }

private:
    int x;
    int y;
    flechette * next;
    std::list<double> dustData;
};

class dust
{
public:
    void setX(int varX)
    {
        x = varX;
    }
    void setY(int varY)
    {
        y = varY;
    }
    dust* getnext()
    {
        return next;
    }
    void makenew()
    {
        next = new dust;
    }
    void setType(int b)
    {
        type = b;
    }
    flechette* getCurr()
    {
        return curr;
    }
    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
    int getType()
    {
        return type;
    }
    double getSig()
    {
        return sig;
    }
    void setSig(double signal)
    {
        sig = signal;
    }
    bool inRange(double range, flechette* current, target* tar)
    {
        //std::cout<<(sqrt(pow(((current->getX())-x),2) + pow(((current->getY())-y),2)))<< " ";
        if((sqrt((pow(((current->getX())-x),2)) + pow(((current->getY())-y),2)) <= range) && (sqrt((pow(((tar->getX())-x),2) +pow(((tar->getY())-y),2) <= range))))
        {
            //std::cout<<"yay";
            if(head == NULL)
            {
                head = current;
                curr = head;
                return true;
            }
            curr = current;
            return true;
        }
        return false;
    }


private:
    double sig;
    int type;
    int x;
    int y;
    dust * next;
    flechette* head = NULL;
    flechette* curr = NULL;
};



#endif //FLECHETTE_H


