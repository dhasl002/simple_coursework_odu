#ifndef ARTIST_H
#define ARTIST_H
#include <iostream>
#include <fstream>
using namespace std;

class artist
{
public:
    artist()
    {
        projectNumberIndex = 1;
        currentTime = 0;
        averageTime = 0;
        multiplier = 0;
        id = 'z';
        totalTime = 0;
        projectNumber = 0;
        startTime = 0;

    }
    int getStartTime()
    {
        return startTime;
    }
    void setMult(double mult)
    {
        multiplier = mult;
    }
    void setID(char i)
    {
        id = i;
    }
    void setAverageTime(int time)
    {
        averageTime = time;
    }
    void setProjectNum(int num)
    {
        projectNumber = num;
    }
    void setStartTime(int start)
    {
        startTime = start;
    }
    artist* getNext()
    {
        return next;
    }
    void compute()
    {
        totalTime = averageTime + (averageTime * multiplier);
    }
    void makeNew()
    {
        next = new artist;
    }
    void printStart(int time)
    {
        cout<< id << '\t' << (startTime) << '\t' << "begins project " << projectNumberIndex << endl;

    }
    void printEnd(int time)
    {
        cout<< id << '\t' << (startTime + totalTime) << '\t' << "finishes project " << projectNumberIndex << endl;
        startTime = time + 1;
        if(projectNumberIndex <= projectNumber)
        {
            projectNumberIndex++;
        }
    }
    int getTotalTime()
    {
        return totalTime;
    }
    char getID()
    {
        return id;
    }
private:
    int currentTime;
    int totalTime;
    int projectNumber;
    int projectNumberIndex;
    int startTime;
    int averageTime;
    double multiplier;
    char id;
    artist * next;
};

#endif //ARTISTS_H
