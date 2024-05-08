package model;

import view.panel;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class file {
    private Random rand= new Random();

    //colleghiamo il model alla view
    private panel pannello;
    public file(panel boardWindow) {
        pannello= boardWindow;
    }

    int rows = 30;
    int cols = 30;

    int[][] matrice = new int[rows][cols];
    Deque<SimpleEntry<Integer, Integer>> deque = new ArrayDeque<>();
    int elementiSnake =0 ;



    public void riempiMatrice()
    {
        for(int i=0;i<matrice.length;i++)
        {
            Arrays.fill(matrice[i], 0);
        }



        pannello.setNullGriglia();

        int size = matrice.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || j == 0 || i == size - 1 || j == size - 1) {
                    matrice[i][j] = 5;
                }
            }
        }

        pannello.setBordi();
    }

    public void stampaMatrice() {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }


    }

    public  ArrayList<SimpleEntry<Integer, Integer>>  controlloMela()
    {
        ArrayList<SimpleEntry<Integer, Integer>> slotDisponibili = new ArrayList<>();

        for(int i=0;i<matrice.length;i++)
        {
            for(int j=0;j<matrice[i].length;j++)
            {
                if (matrice[i][j]!=1 && matrice[i][j]!=5 && matrice[i][j]!=3)
                {
                    slotDisponibili.add(new SimpleEntry<>(i,j));
                }
            }
        }

        return slotDisponibili;

    }



    public SimpleEntry<Integer, Integer>  scelgoMela(ArrayList<SimpleEntry<Integer, Integer>> slot)
    {


        int dimensione = slot.size();
        int indice = rand.nextInt(dimensione);

        int i = slot.get(indice).getKey();
        int j = slot.get(indice).getValue();

        matrice[i][j] =2;
        pannello.modificaPosizioneMela(i,j);
        return slot.get(indice);

    }



    public void creoTestaSnake()
    {
        matrice[1][1]=1;
        pannello.creaSnake();

    }
    public SimpleEntry<Integer,Integer> cercoTestaSnake() {

        SimpleEntry<Integer, Integer> testa = null;
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                if (matrice[i][j] == 1) {
                    testa = new SimpleEntry<>(i, j);
                }
            }
        }

        return testa;
    }

    public void muovoSnakeSotto(SimpleEntry<Integer,Integer> testa)
    {
        int control = 0;
        int indiceRiga = testa.getKey();
        int indiceColonna = testa.getValue();

        matrice[indiceRiga][indiceColonna]=0;

        if (matrice[indiceRiga+1][indiceColonna]==5 || matrice[indiceRiga+1][indiceColonna]==3 )
        {
            haiPerso();
        }
        else
        {
            if (matrice[indiceRiga+1][indiceColonna]==2)
            {
                matrice[indiceRiga + 1][indiceColonna] = 1;
                int check_finale = controlloMangioMela(indiceRiga, indiceColonna);
                if (check_finale==1)
                {
                    return;
                }
                control = 1;
            }


            if (control!=1 && elementiSnake!=0)
            {
                spostaCorpo(indiceRiga,indiceColonna);
            }

            matrice[indiceRiga + 1][indiceColonna] = 1;

            pannello.snakeSotto(indiceRiga, indiceColonna);
            pannello.stampaLista(deque);
            control =0;
        }
    }

    private void haiPerso() {
        pannello.haiPerso();
    }

    public void muovoSnakeSopra(SimpleEntry<Integer,Integer> testa)
    {
        int control = 0 ;
        int indiceRiga = testa.getKey();
        int indiceColonna = testa.getValue();


        matrice[indiceRiga][indiceColonna]=0;

        if (matrice[indiceRiga-1][indiceColonna]==5 || matrice[indiceRiga-1][indiceColonna]==3 )
        {
            haiPerso();
        }

        else
        {
            if (matrice[indiceRiga-1][indiceColonna]==2)
            {
                matrice[indiceRiga-1][indiceColonna]=1;
                int check_finale = controlloMangioMela(indiceRiga, indiceColonna);
                if (check_finale==1)
                {
                    return;
                }
                control = 1;
            }


            if (control!=1 && elementiSnake!=0)
            {
                spostaCorpo(indiceRiga,indiceColonna);
            }

            matrice[indiceRiga-1][indiceColonna]=1;

            pannello.snakeSopra(indiceRiga,indiceColonna);
            pannello.stampaLista(deque);
            control = 0;
        }



    }

    public void muovoSnakeDestra(SimpleEntry<Integer,Integer> testa)
    {
        int control =0;
        int indiceRiga = testa.getKey();
        int indiceColonna = testa.getValue();



        matrice[indiceRiga][indiceColonna]=0;

        if (matrice[indiceRiga][indiceColonna+1]==5 || matrice[indiceRiga][indiceColonna+1]==3 )
        {
            haiPerso();
        }
        else
        {
            if (matrice[indiceRiga][indiceColonna+1]==2)
            {
                matrice[indiceRiga][indiceColonna+1]=1;

                int check_finale = controlloMangioMela(indiceRiga, indiceColonna);
                if (check_finale==1)
                {
                    return;
                }
                control=1;
            }

            if (control!=1 && elementiSnake!=0)
            {

                spostaCorpo(indiceRiga,indiceColonna);
            }

            matrice[indiceRiga][indiceColonna+1]=1;

            pannello.snakeDestra(indiceRiga,indiceColonna);
            pannello.stampaLista(deque);
            control =0;
        }

    }

    public void muovoSnakeSinistra(SimpleEntry<Integer,Integer> testa)
    {
        int control =0;
        //fare controlli sui bordi per tutte le funzioni muovi snake
        int indiceRiga = testa.getKey();
        int indiceColonna = testa.getValue();
        matrice[indiceRiga][indiceColonna]=0;

        if (matrice[indiceRiga][indiceColonna-1]==5 || matrice[indiceRiga][indiceColonna-1]==3  )
        {
            haiPerso();
        }
        else
        {
            if(matrice[indiceRiga][indiceColonna-1]==2)
            {
                matrice[indiceRiga][indiceColonna-1]=1;
                int check_finale = controlloMangioMela(indiceRiga, indiceColonna);
                if (check_finale==1)
                {
                    return;
                }
                control=1 ;
            }



            if (control!=1  && elementiSnake!=0)
            {
                spostaCorpo(indiceRiga,indiceColonna);
            }

            matrice[indiceRiga][indiceColonna-1]=1;

            pannello.snakeSinistra(indiceRiga,indiceColonna);
            pannello.stampaLista(deque);

            control =0 ;
        }

    }

    public int controlloMangioMela(int indiceRiga,int indiceColonna)
    {


        matrice[indiceRiga][indiceColonna]=3;
        deque.addFirst(new SimpleEntry<>(indiceRiga,indiceColonna));
        elementiSnake++;

        ArrayList<SimpleEntry<Integer, Integer>> slotDisponibili = new ArrayList<>();
        slotDisponibili= controlloMela();

        if (!(slotDisponibili.isEmpty()))
        {
            SimpleEntry<Integer,Integer> mela = scelgoMela(slotDisponibili);
            pannello.modificaPosizioneMela(mela.getKey(),mela.getValue());
            return 0;
        }

        pannello.haiVinto();
        return 1;

    }


    public void start() {

        pannello.cancellaLista(deque);
        deque.clear();
        riempiMatrice(); //riempiGriglia sulla view
        creoTestaSnake(); //creaSnake sulla view
        ArrayList<SimpleEntry<Integer, Integer>> slotDisponibili = new ArrayList<>(); //solo per la prima ricerca
        slotDisponibili = controlloMela();
        if (slotDisponibili.isEmpty())
        {
            System.out.println("Inializzazione Fallita");
            return;
        }
        SimpleEntry<Integer,Integer> mela ;
        mela = scelgoMela(slotDisponibili); //stampare la mela sulla view

    }


    public void spostaCorpo(int indiceRiga, int indiceColonna)
    {

        pannello.cancellaLista(deque);
        matrice[indiceRiga][indiceColonna] =3;
        deque.addFirst(new SimpleEntry<>(indiceRiga,indiceColonna));
        SimpleEntry<Integer,Integer> eliminare = deque.removeLast();

        matrice[eliminare.getKey()][eliminare.getValue()]=0;
    }



}
