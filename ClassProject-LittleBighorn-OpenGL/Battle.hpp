#ifndef Battle_hpp
#define Battle_hpp

#include <stdio.h>
#include <map>
#include <list>
#include <vector>
#include <string>
#include <fstream>
#include <iostream>

using namespace std;

struct Position {
    int x;
    int y;

    Position() {
        x = 0;
        y = 0;
    }

    Position(int nx, int ny) {
        x = nx;
        y = ny;
    }
};

class Battle {

    string filename;
    string battleName;
    int currentIteration;

    vector<string> names;
    map<string, vector<Position>> movements;

    vector<Position> readMovementLine(string line);

public:

    void readMovementsFromFile(string newFilename);
    void readMovements();
    void readNames();
    map<string,Position> getPositionAtNextIteration();
    int getCurrentIteration();

};


#endif /* Battle_hpp */
