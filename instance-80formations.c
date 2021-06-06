#include <stdio.h>
                  
#define NBR_INTERFACES        24
#define NBR_APPRENANTS        80
#define NBR_FORMATIONS        80
#define NBR_CENTRES_FORMATION 5
#define NBR_SPECIALITES       5
#define NBR_NODES 	      NBR_CENTRES_FORMATION+NBR_INTERFACES+NBR_APPRENANTS
                  
/* code des compétence en langage des signes et en codage LPC */
#define COMPETENCE_SIGNES     0
#define COMPETENCE_CODAGE     1
                  
/* competences des interfaces en SIGNES et CODAGE*/
int competences_interfaces[NBR_INTERFACES][2]={
    {1,0}, /* compétence en langages des SIGNES mais pas en CODAGE LPC */
    {0,1}, /* pas de compétence en langages des SIGNES mais compétence en CODAGE LPC */
    {1,0},
    {1,0},
    {0,1},
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {1,1},
    {0,1},
    {0,1},
    {1,0},
    {0,1},
    {1,1},
    {0,1},
    {1,0},
    {0,1},
    {0,1},
    {0,1},
    {1,0}
};
                  
/* spécialités des interfaces */
#define SPECIALITE_SANS       -1 /* Enseigné dans le centre le plus proche */
#define SPECIALITE_MENUISERIE 0
#define SPECIALITE_ELECTRICITE 1
#define SPECIALITE_MECANIQUE 2
#define SPECIALITE_INFORMATIQUE 3
#define SPECIALITE_CUISINE 4
                  
/* specialite des interfaces */
int specialite_interfaces[NBR_INTERFACES][NBR_SPECIALITES]={
    {0,0,0,0,0},
    {0,0,0,1,0},
    {0,0,0,1,1},
    {0,0,0,0,0},
    {1,0,0,0,1},
    {1,1,0,1,0},
    {0,0,0,0,0},
    {1,0,0,0,1},
    {0,0,0,1,0},
    {1,0,0,0,0},
    {0,0,1,0,1},
    {0,0,1,1,0},
    {0,1,0,0,0},
    {1,1,0,0,0},
    {1,0,1,0,1},
    {1,0,0,1,1},
    {1,0,0,0,0},
    {0,0,0,0,0},
    {0,0,1,0,1},
    {0,0,0,0,0},
    {0,0,0,0,0},
    {1,0,0,0,0},
    {0,0,0,0,0},
    {0,0,0,0,1}
};
                  
/* coordonnées des centres de formation, des interfaces et des apprenants */
float coord[NBR_NODES][2]={
                  
    /* Les interfaces se rendent du centre SESSAD à l'école de formation */
    {157,25}, /* centre 0 */
                  
    /* Centres de formation */
    {159,86}, /* ecole formation SPECIALITE_MENUISERIE */
    {97,135}, /* ecole formation SPECIALITE_ELECTRICITE */
    {72,116}, /* ecole formation SPECIALITE_MECANIQUE */
    {24,122}, /* ecole formation SPECIALITE_INFORMATIQUE */
    {108,33}, /* ecole formation SPECIALITE_CUISINE */
                  
    /* Apprenants */
    {109,30}, /* apprenant 0 */
    {185,53}, /* apprenant 1 */
    {190,172}, /* apprenant 2 */
    {128,120}, /* apprenant 3 */
    {38,121}, /* apprenant 4 */
    {37,129}, /* apprenant 5 */
    {159,13}, /* apprenant 6 */
    {74,182}, /* apprenant 7 */
    {45,196}, /* apprenant 8 */
    {126,108}, /* apprenant 9 */
    {0,147}, /* apprenant 10 */
    {141,75}, /* apprenant 11 */
    {70,16}, /* apprenant 12 */
    {45,111}, /* apprenant 13 */
    {174,35}, /* apprenant 14 */
    {190,136}, /* apprenant 15 */
    {14,112}, /* apprenant 16 */
    {88,151}, /* apprenant 17 */
    {9,12}, /* apprenant 18 */
    {178,173}, /* apprenant 19 */
    {136,0}, /* apprenant 20 */
    {49,15}, /* apprenant 21 */
    {122,150}, /* apprenant 22 */
    {86,126}, /* apprenant 23 */
    {147,180}, /* apprenant 24 */
    {54,49}, /* apprenant 25 */
    {147,38}, /* apprenant 26 */
    {25,184}, /* apprenant 27 */
    {4,108}, /* apprenant 28 */
    {102,74}, /* apprenant 29 */
    {144,107}, /* apprenant 30 */
    {194,10}, /* apprenant 31 */
    {4,190}, /* apprenant 32 */
    {50,77}, /* apprenant 33 */
    {144,178}, /* apprenant 34 */
    {87,137}, /* apprenant 35 */
    {54,117}, /* apprenant 36 */
    {22,11}, /* apprenant 37 */
    {125,157}, /* apprenant 38 */
    {118,99}, /* apprenant 39 */
    {38,64}, /* apprenant 40 */
    {81,47}, /* apprenant 41 */
    {160,51}, /* apprenant 42 */
    {149,69}, /* apprenant 43 */
    {41,179}, /* apprenant 44 */
    {32,109}, /* apprenant 45 */
    {129,137}, /* apprenant 46 */
    {11,50}, /* apprenant 47 */
    {68,0}, /* apprenant 48 */
    {103,144}, /* apprenant 49 */
    {54,148}, /* apprenant 50 */
    {185,102}, /* apprenant 51 */
    {107,98}, /* apprenant 52 */
    {75,40}, /* apprenant 53 */
    {125,21}, /* apprenant 54 */
    {47,150}, /* apprenant 55 */
    {69,110}, /* apprenant 56 */
    {106,168}, /* apprenant 57 */
    {193,158}, /* apprenant 58 */
    {126,154}, /* apprenant 59 */
    {171,148}, /* apprenant 60 */
    {107,143}, /* apprenant 61 */
    {99,47}, /* apprenant 62 */
    {100,82}, /* apprenant 63 */
    {176,47}, /* apprenant 64 */
    {36,85}, /* apprenant 65 */
    {97,112}, /* apprenant 66 */
    {69,179}, /* apprenant 67 */
    {170,143}, /* apprenant 68 */
    {61,73}, /* apprenant 69 */
    {61,107}, /* apprenant 70 */
    {121,64}, /* apprenant 71 */
    {192,135}, /* apprenant 72 */
    {38,183}, /* apprenant 73 */
    {23,50}, /* apprenant 74 */
    {46,44}, /* apprenant 75 */
    {71,43}, /* apprenant 76 */
    {193,92}, /* apprenant 77 */
    {33,107}, /* apprenant 78 */
    {111,86}/* apprenant 79 */
};
                  
#define NBR_FORMATION          80
                  
#define LUNDI                  1
#define MARDI                  2
#define MERCREDI               3
#define JEUDI                  4
#define VENDREDI               5
#define SAMEDI                 6
                  
/* formation : id formation, specialite ou centre de formation, competence, horaire debut formation, horaire fin formation */
int formation[NBR_FORMATION][6]={
   {0,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,15,18},
   {1,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MERCREDI,15,19},
   {2,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,LUNDI,15,18},
   {3,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,JEUDI,10,12},
   {4,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,SAMEDI,13,18},
   {5,SPECIALITE_CUISINE,COMPETENCE_CODAGE,VENDREDI,13,17},
   {6,SPECIALITE_CUISINE,COMPETENCE_SIGNES,JEUDI,15,17},
   {7,SPECIALITE_INFORMATIQUE,COMPETENCE_CODAGE,SAMEDI,16,19},
   {8,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,VENDREDI,8,10},
   {9,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,VENDREDI,10,12},
   {10,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,VENDREDI,10,12},
   {11,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,VENDREDI,8,11},
   {12,SPECIALITE_INFORMATIQUE,COMPETENCE_CODAGE,LUNDI,16,19},
   {13,SPECIALITE_CUISINE,COMPETENCE_SIGNES,MERCREDI,14,18},
   {14,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,JEUDI,14,17},
   {15,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,MERCREDI,14,17},
   {16,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,LUNDI,9,12},
   {17,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,9,12},
   {18,SPECIALITE_CUISINE,COMPETENCE_CODAGE,SAMEDI,9,12},
   {19,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,VENDREDI,15,19},
   {20,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,LUNDI,14,18},
   {21,SPECIALITE_INFORMATIQUE,COMPETENCE_CODAGE,SAMEDI,13,19},
   {22,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,MERCREDI,16,18},
   {23,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MERCREDI,10,12},
   {24,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,VENDREDI,15,17},
   {25,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,LUNDI,15,18},
   {26,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,SAMEDI,16,19},
   {27,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,14,18},
   {28,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,JEUDI,9,11},
   {29,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,SAMEDI,15,19},
   {30,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MARDI,13,16},
   {31,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,SAMEDI,15,17},
   {32,SPECIALITE_CUISINE,COMPETENCE_SIGNES,VENDREDI,16,19},
   {33,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,8,10},
   {34,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,SAMEDI,16,18},
   {35,SPECIALITE_INFORMATIQUE,COMPETENCE_CODAGE,VENDREDI,9,11},
   {36,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MERCREDI,13,19},
   {37,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,LUNDI,15,17},
   {38,SPECIALITE_CUISINE,COMPETENCE_SIGNES,VENDREDI,9,12},
   {39,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,MERCREDI,9,12},
   {40,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,JEUDI,10,12},
   {41,SPECIALITE_CUISINE,COMPETENCE_SIGNES,SAMEDI,8,10},
   {42,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MARDI,14,17},
   {43,SPECIALITE_INFORMATIQUE,COMPETENCE_CODAGE,MARDI,9,12},
   {44,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MERCREDI,13,16},
   {45,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,SAMEDI,13,19},
   {46,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,10,12},
   {47,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,MERCREDI,16,18},
   {48,SPECIALITE_INFORMATIQUE,COMPETENCE_CODAGE,MERCREDI,8,11},
   {49,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,SAMEDI,13,15},
   {50,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,JEUDI,16,18},
   {51,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,VENDREDI,15,18},
   {52,SPECIALITE_CUISINE,COMPETENCE_CODAGE,LUNDI,8,11},
   {53,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,9,11},
   {54,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,VENDREDI,9,11},
   {55,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,VENDREDI,13,18},
   {56,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MARDI,13,18},
   {57,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,10,12},
   {58,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,JEUDI,13,19},
   {59,SPECIALITE_CUISINE,COMPETENCE_SIGNES,SAMEDI,10,12},
   {60,SPECIALITE_CUISINE,COMPETENCE_CODAGE,SAMEDI,9,12},
   {61,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,LUNDI,10,12},
   {62,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MERCREDI,8,11},
   {63,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,9,12},
   {64,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,MERCREDI,13,17},
   {65,SPECIALITE_CUISINE,COMPETENCE_SIGNES,MARDI,9,11},
   {66,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,VENDREDI,9,12},
   {67,SPECIALITE_INFORMATIQUE,COMPETENCE_CODAGE,MARDI,13,18},
   {68,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,13,18},
   {69,SPECIALITE_CUISINE,COMPETENCE_CODAGE,MERCREDI,13,18},
   {70,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,VENDREDI,9,11},
   {71,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,15,17},
   {72,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,SAMEDI,9,12},
   {73,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,9,11},
   {74,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,VENDREDI,8,11},
   {75,SPECIALITE_CUISINE,COMPETENCE_SIGNES,JEUDI,9,12},
   {76,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,MERCREDI,10,12},
   {77,SPECIALITE_INFORMATIQUE,COMPETENCE_SIGNES,SAMEDI,13,18},
   {78,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,MARDI,13,19},
   {79,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,16,19}
};
                  
int main() {
                  
    printf("NBR_INTERFACES=%d\n",NBR_INTERFACES) ;
    printf("NBR_APPRENANTS=%d\n",NBR_APPRENANTS) ;
    printf("NBR_FORMATIONS=%d\n",NBR_FORMATIONS) ;
    printf("NBR_NODES=%d\n",NBR_NODES) ;
                  
    return 0 ;
}
                  
