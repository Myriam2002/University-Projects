#include<bits/stdc++.h>
using namespace std;
// global variables
const int N=2e5+5;
int visited[N]; // used to know if this node was traversed or not, this will help it terminating the recursion
vector<int> adjaceny_list[N]; // stores the adjacency list input
// we use depth first search to traverse many times to see if there are many components connected or not
void depth_first_search(int u)
{
	visited[u]= true;
	for(auto v: adjaceny_list[u])
	{
		if(!visited[v])
			depth_first_search(v);
	}

}
//===== main
int main()
{
    cout << "\t\t=========================Connected Components Algorithm=========================\n\n";
    int nodes, edges;
    int counter_components=0;
    cout <<"Please input the number of nodes and edges respectively:\t";
    cin >> nodes >> edges;
    // insert the elements in the adjacency list
    for(int i=0; i<edges; i++)
    {
        int u,v;
        cout << "Node :\t";
        cin >> u;
        cout << "Adjacent node :\t";
         cin>> v;
        adjaceny_list[u].push_back(v);  // inputed like so because this is an undirected graph
        adjaceny_list[v].push_back(u);
    }

    for(int i=0; i<nodes; i++)
    {
        if(!visited[i])
        {
            depth_first_search(i);
            counter_components++;
        }

    }
    if(counter_components >1 )
    {
        cout<< "This graph is not connected"<< endl;
        cout << "Number of connected components\t"<< counter_components<< endl;
    }

    else
        cout <<"This graph is connected"<<endl;

	return 0;
}
