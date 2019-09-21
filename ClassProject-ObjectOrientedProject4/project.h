#ifndef PROJECT_H
#define PROJECT_H

#include <iostream>
#include <string>
#include "staff.h"


class Division;


class Project {
public:

  typedef const Staff* iterator;

  Project(int maxStaff = DEFAULT_MAXSTAFF);
  ~Project();


  // Attributes of Project

  // Project title
  std::string getTitle() const     {return theTitle;}
  void setTitle(std::string title) {theTitle = title;}

  // To which division does this project belong? Each project belongs
  // to one division (hence, the division data are pointers to
  // shared Division objects), but a division may have many projects.
  Division* getDivision() const    {return theDivision;}
  void setDivision(Division* publ)  {theDivision = publ;}

  // The project ID is a unique identifier for the project.
  std::string getProjectID() const     {return theProjectID;}
  void setProjectID(std::string projectID)  {theProjectID = projectID;}

  // Budget codes indicate to whom this project is charged.
  std::string getBudgetCode() const    {return theBudgetCode;}
  void setBudgetCode(std::string projectID) {theBudgetCode = projectID;}


  // Operations on Project

  // Add a staff member to the project. Staff should be kept in alphabetic
  // order by name.
  void addStaff(const Staff&);
  int numberOfStaff() const;

  // Access to list of staff assigned to this project
  iterator begin() const;
  iterator end() const;
  bool operator< (const Project& right) const;

private:
  std::string theTitle;
  std::string theProjectID;
  std::string theBudgetCode;
  Division* theDivision;

  int numStaff;
  int theMaxStaff;
  Staff* staff;  // array of Staff

  static const int DEFAULT_MAXSTAFF = 12;
};

std::ostream& operator<< (std::ostream& out,  const Project& prj);

#endif
