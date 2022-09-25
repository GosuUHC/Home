#include <stdio.h>
#include "iostream"
#include "../include/mpi.h"

using namespace std;

int main(int argc, char *argv[])
{
    int rank;
    int size;
    MPI_Status stat;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    if (rank == 0)
    {
        int *a = new int[3 * size];

        for (int i = 0; i < (3 * size); i++)
        {
            a[i] = i;
        }

        for (int i = 0; i < (3 * size); i++)
        {
            if (i % size != 0)
            {
                MPI_Send(&a[i], 1, MPI_INT, (i % size), 777, MPI_COMM_WORLD);

            } 
        }

        printf("rank=%d a: ", rank);
        for (int i = 0; i < 3 * size; i++)
        {
            cout << a[i] << " ";
        }
        cout << endl;

        delete[] a;
    }
    else
    {
        int *b = new int[size];

        for (int i = 0; i < size; i++)
        {
            MPI_Recv(&b[i], 1, MPI_INT, 0, 777, MPI_COMM_WORLD, &stat);
        }

        printf("rank=%d b: ", rank);
        for (int i = 0; i < size; i++)
        {
            cout << b[i] << " ";
        }
        cout << endl;

        delete[] b;
    }

    MPI_Finalize();

    return 0;
}
