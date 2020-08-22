#include <bits/stdc++.h>
using namespace std;

vector<int> visitedA(500009 , 0) ;
vector<int> visitedB(500009 , 0);
vector<int> adj[500009];
vector<int> s;
double ans = 0.0;

void initialize(){
    ans = 0.0;
    for(int i=0;i<500009;i++){
        visitedB[i]=0;
        visitedA[i]=0;
        adj[i].clear();
    }
}
void dfs(int node ,int a,int b){
    s.push_back(node);
    for(auto child: adj[node]){
        dfs(child , a,b);
    }
    visitedA[node]+=1;
    visitedB[node]+=1;
    s.pop_back();
    if(s.size() >=a)
        visitedA[s[s.size()-a]]+= visitedA[node];
    if(s.size() >=b)
        visitedB[s[s.size()-b]]+= visitedB[node];
    ans+=1.0 - (1.0 - ((double)visitedA[i])/n)*(1.0  - ((double)visitedB[i])/n);

}

int main() {
    int t;
    cin>>t;
    for(int z=1;z<=t;z++){
        initialize();
        int n,a,b;
        cin>>n>>a>>b;
        for(int i=0;i<n-1;i++){
        int c;  cin>>c;
        adj[c].push_back(i+2);
        }
        dfs(1,a,b);
        cout<<fixed;
        cout<<"Case #"<<z<<": "<<ans<<"\n";
    }
    return 0;
}
