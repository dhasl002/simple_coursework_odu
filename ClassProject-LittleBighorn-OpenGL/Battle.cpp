#include "Battle.hpp"
#include <sstream>

void Battle::readMovementsFromFile(string newFilename) {
    filename = newFilename;
    readMovements();
}

void Battle::readMovements() {
    fstream infile;
    infile.open(filename);
    string line;
    int i = 0;
    while (getline(infile, line)) {
        if(i < names.size()) {
            movements[names[i]] = readMovementLine(line);
            cerr << "read " << names[i] << endl;
        } else {
            movements[to_string(i - names.size())] = readMovementLine(line);
            cerr << "read # " << names[names.size()-1] << " " << i -16 << endl;
        }
        i++;
    }

}

void Battle::readNames() {
    fstream infile;
    infile.open("names.data");
    string line;
    while (getline(infile, line)) {
        names.push_back(line);
    }
}

vector<Position> Battle::readMovementLine(string line) {
    string currX;
    string currY;

    vector<Position> movements;

    bool onY = false;
    for (int i = 0; i < line.length(); i++) {
        if (line[i] != ';') {
            if (line[i] != ',') {
                if (onY) {
                    currY += line[i];
                } else {
                    currX += line[i];
                }
            } else {
                onY = true;
            }

        } else if(line[i] == '$'){
            movements.push_back(Position(-1, -1));
        } else {
            if (currX != "" && currY != "") {
                movements.push_back(Position(atoi(currX.c_str()), atoi(currY.c_str())));
            } else {
                if (movements.size() > 0) {
                    movements.push_back(movements[movements.size()-1]);
                }
            }
            onY = false;
            currX = "";
            currY = "";
        }
    }

    for (int i = 0; i < movements.size(); i++) {
        cout << "(" << movements[i].x << ", " << movements[i].y << "), ";
    }
    cout << endl;
    return movements;

}

map<string,Position> Battle::getPositionAtNextIteration() {
    map<string,Position> ret;

    if (currentIteration < 32) {
        for (auto iter = movements.begin(); iter != movements.end(); ++iter) {
            ret[iter->first] = iter->second[currentIteration];
        }
    }

    currentIteration++;
    return ret;
}

int Battle::getCurrentIteration() {
    return currentIteration;
}

/*
map<string,int> Battle::getPositionAtNextIteration(int iteration) {

}
*/
