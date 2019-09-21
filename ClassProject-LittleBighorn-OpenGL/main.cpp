#include <iostream>
#ifdef WIN32
#include <windows.h>
#endif

#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <string>
#include "Battle.hpp"

#ifdef __APPLE__
#include <GLUT/glut.h>
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#else
#include <GL/glut.h>
#include <GL/glu.h>
#include <GL/gl.h>
#endif

using namespace std;

int currentTime = 100;
Battle bigHornBattle;
bool drawn = false;

int n;
int m;
int *image;
GLdouble ox=0.0,oy=0.0,oz=0.0;

void myInit (void);
void display(void);
void myreshape(int h, int w);
void Mouse(int button,int state,int x,int y);
void keyPressed (unsigned char key, int x, int y);
void updateMousePosition();
void drawCharacterAtPosition(string character, int x, int y);
void drawTroops();
void drawLegend();

int main(int argc, char**argv) {
    FILE *fd;
    int k, nm;
    char c;
    char b[70];
    float s;
    char red, green, blue;
    int x, y;

    fd = fopen("lb.ppm", "r");
    if(fd == 0)
    {
        exit(0);
    }

    fscanf(fd, "%s", b);
    if((b[0] != 'P') || (b[1] != '6'))
    {
        printf("%s is not a PPM file!\n", b);
        exit(0);
    }

    fscanf(fd, "%c", &c);
    fscanf(fd, "%c", &c);

    while(c == '#')
    {
        fscanf(fd, "%[^\n]", b);
        printf("%s\n", b);
        fscanf(fd, "%c", &c);
        printf("%c", c);
    }

    ungetc(c,fd);

    fscanf(fd, "%d %d %d", &n, &m, &k);

    printf("%d rows  %d colums  max value = %d\n", n, m, k);

    nm = n*m;

    image = (int*)malloc(3*sizeof(GLint)*nm);

    s = 255./k;

    for(x = 0; x < m; x++)
    {
        for(y = n-1; y >= 0; y--)
        {
            fscanf(fd, "%c", &red);
            fscanf(fd, "%c", &green);
            fscanf(fd, "%c", &blue);

            image[3*nm - 3*(x*n +y) -3] = green;
            image[3*nm - 3*(x*n +y) -2] = blue;
            image[3*nm - 3*(x*n +y) -1] = red;

        }
    }

    bigHornBattle.readNames();
    bigHornBattle.readMovementsFromFile("movement.data");

    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(n,m);
    glutInitWindowPosition(0,0);
    glutCreateWindow("Little BigHorn");
    myInit();
    glutReshapeFunc(myreshape);
    glutDisplayFunc(display);

    glPixelTransferf(GL_RED_SCALE, s);
    glPixelTransferf(GL_GREEN_SCALE, s);
    glPixelTransferf(GL_BLUE_SCALE, s);
    glPixelStorei(GL_UNPACK_SWAP_BYTES, GL_TRUE);
    glClearColor(1.0, 1.0, 1.0, 1.0);

    glutMouseFunc(Mouse);
    glutKeyboardFunc(keyPressed);
    glutMainLoop ( );

}


void myInit (void) {
    glClearColor ( 1.0, 1.0, 1.0 , 1.0);
    glColor3f ( 1.0f, 0.0f, 0.0f );
    glPointSize ( 4.0 );
    glMatrixMode ( GL_PROJECTION );
    glLoadIdentity ( );
    gluOrtho2D ( 0.0, 400.0, 1.0, 400.0 );

}


void display(void) {

    glClear ( GL_COLOR_BUFFER_BIT );
    glRasterPos2i(0,0);
    glDrawPixels(n,m,GL_RGB, GL_UNSIGNED_INT, image);
    glPointSize(10.0);

    updateMousePosition();
    if (drawn == false) {
        drawTroops();
        drawn = true;
    } else {
        drawn = false;
    }

    drawLegend();
    glFlush ( );

}

void drawLegend() {
    int i = 20;
    int j = 15;
    drawCharacterAtPosition("* - custer's headquarters unit", 25, i + j);
    drawCharacterAtPosition("F,C,E,I,L - custer's command", 25,i + 2*j);
    drawCharacterAtPosition("A,M,g - reno's command", 25, i+3*j);
    drawCharacterAtPosition("H,D,K - benteen's command", 25, i+4*j);
    drawCharacterAtPosition("B - packtrain guard", 25, i+5*j);
    drawCharacterAtPosition("P - packtrain (cavalry unit)", 25, i+6*j);
    drawCharacterAtPosition("C - crazy horse,  oglala warchief", 25, i+7*j);
    drawCharacterAtPosition("G - gall, hunkpapa warchief", 25, i+8*j);
    drawCharacterAtPosition("1-34 - indian units", 25, i+9*j);
    string minutes = to_string((bigHornBattle.getCurrentIteration() * 10 + 80) % 60);
    if(minutes == "0") minutes = "00";
    drawCharacterAtPosition("Elapsed Time - " + to_string((bigHornBattle.getCurrentIteration()*10+80)/60)+ ":" + minutes, 0, 880);
}

void drawTroops() {
    auto curriter = bigHornBattle.getPositionAtNextIteration();
    for (auto iter = curriter.begin(); iter!= curriter.end(); ++iter) {
        drawCharacterAtPosition(iter->first, iter->second.x, iter->second.y);
    }
}

void drawCharacterAtPosition(string character, int x, int y) {
    glMatrixMode(GL_PROJECTION);
    glPushMatrix();
    glLoadIdentity();
    gluOrtho2D(0.0, 1152, 0.0, 900);
    glMatrixMode(GL_MODELVIEW);
    glPushMatrix();
    glLoadIdentity();
    glRasterPos2i(x, 900-y);
    string test = character;
    void * font = GLUT_BITMAP_9_BY_15;
    for (string::iterator i = test.begin(); i != test.end(); ++i)
    {
        char c = *i;
        glutBitmapCharacter(font, c);
    }
    glMatrixMode(GL_MODELVIEW);
    glPopMatrix();

    glMatrixMode(GL_PROJECTION);
    glPopMatrix();

}

void updateMousePosition() {
    glMatrixMode(GL_PROJECTION);
    glPushMatrix();
    glLoadIdentity();
    gluOrtho2D(0.0, 1152, 0.0, 900);
    glMatrixMode(GL_MODELVIEW);
    glPushMatrix();
    glLoadIdentity();
    glRasterPos2i(0, 0);
    string position = "X: " + to_string(ox) + ", Y: " + to_string(900-oy);
    void * font = GLUT_BITMAP_9_BY_15;
    for (string::iterator i = position.begin(); i != position.end(); ++i)
    {
        char c = *i;
        glutBitmapCharacter(font, c);
    }
    glMatrixMode(GL_MODELVIEW);
    glPopMatrix();

    glMatrixMode(GL_PROJECTION);
    glPopMatrix();

}

void myreshape(int h, int w) {
    glMatrixMode (GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0.0, (GLfloat) n, 0.0, (GLfloat) m);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glViewport(0,0,h,w);
}

void Mouse(int button,int state,int x,int y) {
    GLint viewport[4];
    GLdouble modelview[16],projection[16];
    GLfloat wx=x,wy,wz;

    if(state!=GLUT_DOWN)
        return;
    if(button==GLUT_RIGHT_BUTTON)
        exit(0);
    glGetIntegerv(GL_VIEWPORT,viewport);
    y=viewport[3]-y;
    wy=y;
    glGetDoublev(GL_MODELVIEW_MATRIX,modelview);
    glGetDoublev(GL_PROJECTION_MATRIX,projection);
    glReadPixels(x,y,1,1,GL_DEPTH_COMPONENT,GL_FLOAT,&wz);
    gluUnProject(wx,wy,wz,modelview,projection,viewport,&ox,&oy,&oz);
    glutPostRedisplay();
    cout << "X: " << ox << ", Y: "<< oy << endl;
    display();
}

void keyPressed (unsigned char key, int x, int y) {
    currentTime++;
    display();

}

