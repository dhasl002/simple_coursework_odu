#include <iostream>
#include <fstream>
#include "flechette.h"
#include <string>
#include <chrono>
#include <random>

using namespace std;

int main()
{
const int TOTALDUST = 1000;
const double TOTALFLECHETTES = TOTALDUST/10.0;
double delta = 400.0/TOTALDUST;


double range;
fstream fin;
ofstream testoutput;
ofstream Thetarget;

testoutput.open("testOutput.txt");
fin.open("range.txt", ios::in);
fin >> range;
 unsigned seed = std::chrono::system_clock::now().time_since_epoch().count();
  std::default_random_engine generator(seed);
  std::uniform_int_distribution<int> distributionb(0,3);
  std::normal_distribution<double> distributiony(0,50);
  std::uniform_int_distribution<int> distributionx (0,2);
  std::uniform_int_distribution<int> distributionz(-200,200);
int z;
z = distributionz(generator);
int b;
double x=-200.0,y;
dust * headD = NULL;
dust * currD = NULL;
dust * temp = NULL;
flechette * headF = NULL;
flechette * currF = NULL;
target * headT = NULL;
target * currT = NULL;
target * target1 = NULL;
headT = new target;
target1 = new target;
headF = new flechette;
currT = target1;
x = x + delta*distributionx(generator);
y = distributiony(generator);
headF->setX(x);
headF->setY(y);
currF = headF;
headD = new dust;
x = -200 + delta*distributionx(generator);
y = distributiony(generator);
headD->setX(x);
headD->setY(y);
currD = headD;


for(int i=2; i<TOTALDUST; i++)
{
    x += delta*distributionx(generator);
    y = distributiony(generator);
    b = distributionb(generator);
    currD->makenew();
    currD=currD->getnext();
    currD->setType(b);
    currD->setX(x);
    currD->setY(y);
    if (x>= 200)
        i=TOTALDUST;
    testoutput<< x << " " << y << " " << endl;
}
testoutput.close();
testoutput.open("testoutput2.txt");
delta = 400.0/TOTALFLECHETTES;
x= -200;
y= 0;

for (int i=1; i<TOTALFLECHETTES; i++)
{
    x = x + delta*distributionx(generator);
    y = distributiony(generator);
    currF->makenew();
    currF=currF->getnext();
    currF->setX(x);
    currF->setY(y);
    if (x>= 200)
        i=TOTALFLECHETTES;
   testoutput<< x << " " << y << " " << endl;

}
Thetarget.open("targetList.txt");
while (target1->getX() < 200)
{
    //cout<< target1->getX();
currF = headF;
currD = headD;
//adds dust to flechette lists
for (int i=1; i<TOTALDUST; i++)
{
    //cout<<(currD->getX()) << " ";
    for (int j=1; j<TOTALFLECHETTES; j++)
    {
        //cout<<(currF->getX()) << " ";
        if(currD->inRange(range,currF,currT) && currD->getType() !=2)
        {
            //cout<< "inRange" << endl;

            if(currD->getType() == 0)
            {
                currD->setSig(target1->getProfile1());
            }
            if(currD->getType() == 1)
            {
                currD->setSig(target1->getProfile2());
            }
            if(currD->getType() == 3)
            {
                currD->setSig(target1->getProfile4());
            }
            //cout<<(currT->getX())<< " " << (currT->getY())<< "\n" << endl;
            //cout<<(currD->getX())<< " " << (currD->getY()) << endl;
            flechette* temp = currD->getCurr();
            temp->addToList((currD->getX()),(currD->getY()),(currD->getSig()));
            if(currF->getnext()!=NULL)
            {
                currF = currF->getnext();
            }

        }

        if(currF->getnext()!=NULL)
        {
            currF = currF->getnext();
        }

    }
    //cout<<"yo";
    currF = headF;
    if(currD->getnext()!=NULL)
    {
        currD = currD->getnext();
    }
    currF = headF;
}

currF = headF;
currD = headD;
//sends all flechette packets to command
for (int i=1; i<TOTALFLECHETTES; i++)
{
    //cout<<"yay";
    currF->sendToCommand();
    currF->getnext();
}
    Thetarget<< (target1->getX()) << " " << (target1->getY()) << " " << "T" << endl;
    target1->moveTarget(z);
    //cout<<(target1->getX());
    //cout<<(target1->getY());
}

fin.close();
return 0;
}







