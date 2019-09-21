#include "division.h"
#include "project.h"

#include <algorithm>
#include <cassert>
#include <cstdlib>

using namespace std;

Project::Project(int maxStaff)
: theProjectID(""), theBudgetCode(""), theDivision(0), numStaff(0),
    theMaxStaff(maxStaff)
{
  staff = new Staff[maxStaff];
}

Project::~Project()
{
  delete staff;
}

Project::iterator Project::begin() const
{
  return staff;
}


Project::iterator Project::end() const
{
  return staff + numStaff;
}






/**** insert your operations for Projects here  ***/
ostream& operator<< (ostream& out, const Project& prj)
{

    out << prj.getTitle() << "\t"
	      << prj.getDivision() << "\t"
	      << prj.getProjectID() << "\t"
	      << prj.getBudgetCode() << "\t"
	      << "\n";
	  return out;
}

bool Project::operator< (const Project& right) const
{
  if(theProjectID < right.getProjectID())
  {
      return true;
  }
      return false;
}

void Project::addStaff(const Staff& staffMember)
{
    staff[numStaff]=staffMember;
  numStaff++;
}

int Project::numberOfStaff() const
{
    return numStaff;
}


