import networkx as nx

class GraphDfsUsingTimeStamp:
    def __init__(self, edges: list, dfs_order: list, has_loop: list, dfs_traversal_output_file):
        self._g = nx.Graph()  # Use nx.Graph for an undirected graph
        self._dfs_order = dfs_order
        self._has_loop = has_loop
        self._has_loop.append(False)
        self._dfs_traversal_output_file = dfs_traversal_output_file

        self._counter = 0
        self._a = {}  # Parallel dictionary. Key is node. Value is Timestamp
        self._set_of_unvisited_nodes = set()

        self._build_graph(edges)
        self._dfs()
        self._write_dot()

    def _build_graph(self, edges):
        for edge in edges:
            u, v, *weight = edge  # Unpack the edge tuple

            if weight:
                self._g.add_edge(u, v, weight=weight[0])
            else:
                self._g.add_edge(u, v)
                self._g.add_edge(v, u)  # Add both (u, v) and (v, u) for undirected graph

    def _write_dot(self):
        nx.drawing.nx_pydot.write_dot(self._g, self._dfs_traversal_output_file)

    def _dfs(self):
        def dfs_visit(node, parent=None):
            self._counter += 1
            self._a[node] = self._counter
            self._set_of_unvisited_nodes.remove(node)
            self._dfs_order.append(node)

            for neighbor in self._g.neighbors(node):
                if neighbor == parent:  # Skip the parent node (for undirected edges)
                    continue
                if neighbor in self._set_of_unvisited_nodes:
                    dfs_visit(neighbor, node)  # Pass the current node as the parent
                elif self._a[neighbor] == 0:  # Back edge found
                    self._has_loop[0] = True

            self._counter += 1

        for node in self._g.nodes():
            self._set_of_unvisited_nodes.add(node)
            self._a[node] = 0

        for node in self._g.nodes():
            if node in self._set_of_unvisited_nodes:
                dfs_visit(node)

# Test with the provided input (weighted directed graph) and unweighted input
weighted_edges = [
    ('A', 'B', 5),
    ('A', 'C', 3),
    ('B', 'C', 11),
    ('B', 'D', 7),
    ('C', 'D', 7),
    ('C', 'E', 3),
    ('D', 'E', 2),
    ('D', 'F', 6),
    ('E', 'F', 5),
    ('E', 'G', 7),
    ('F', 'G', 7)
]

unweighted_edges = [
   (1, 0),
    (1, 2),
    (0, 3),
    (3, 4),
    (3, 7),
    (4, 5),
    (4, 6),
    (4, 7),
    (5, 6),
    (6, 7)
]

dfs_order = []
has_loop = []
dfs_traversal_output_file = "dfs_output.dot"

# Test with weighted edges
dfs1 = GraphDfsUsingTimeStamp(weighted_edges, dfs_order, has_loop, dfs_traversal_output_file)

print("DFS ORDER (Weighted Graph):", dfs_order)
print("Has Loop (Weighted Graph):", has_loop[0])

# Test with unweighted edges
dfs_order = []
has_loop = []
dfs_traversal_output_file = "dfs_output_unweighted.dot"

dfs2 = GraphDfsUsingTimeStamp(unweighted_edges, dfs_order, has_loop, dfs_traversal_output_file)

print("DFS ORDER (Unweighted Graph):", dfs_order)
print("Has Loop (Unweighted Graph):", has_loop[0])
