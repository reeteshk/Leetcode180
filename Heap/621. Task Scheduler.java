/*
 621. Task Scheduler
Medium
8.6K
1.7K
Companies
Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.
Example 3:

Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation: 
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 

Constraints:

1 <= task.length <= 104
tasks[i] is upper-case English letter.
The integer n is in the range [0, 100].

 */

class Solution {
    public int leastInterval(char[] tasks, int n) {
        char arr[]=new char[26];
        int max_freq=0, count_maxfreq=0, sz=tasks.length;
         for(char i:tasks){
            arr[i-'A']++;  // count the number of times a task needs to be done
            if(arr[i-'A']>max_freq){
                max_freq=arr[i-'A']; // find maximum frequency 
            }
        }   
        for(int i=0;i<26;i++){
            if(arr[i]==max_freq) count_maxfreq++; // number of tasks having maximum frequency
        }
        int time= (max_freq-1)*(n+1)+count_maxfreq; // total time taken to complete all tasks
        return Math.max(time,sz);
    }
}

/*
Method 2
 class Solution {
    class Task {
        int freq, lastUsed = -1;
        public Task(int f) { freq = f; }
    }
    
    // Overall we always want to schedule the most repeated task that is available to schedule every time.
    // This greedy approach works because picking any other task will result in non optimal solution.
    public int leastInterval(char[] tasks, int n) {
        // if n == 0 there will be no idle periods, so return length of tasks
        if(n == 0) return tasks.length;
        
        Map<Character, Task> map = new HashMap<>();
        for(char c: tasks) {
            map.putIfAbsent(c, new Task(0));
            map.get(c).freq++;
        }
        
        PriorityQueue<Task> pq = new PriorityQueue<>((x, y) -> y.freq - x.freq);
        
        // Use a queue to add tasks that were scheduled at the end
        // Which means tasks that are at the top of the cooling are the ones first to go out of cooling and become available for scheduling.
        Queue<Task> cooling = new LinkedList<>();
        
        pq.addAll(map.values());
        int count = 0;
        
        while(!pq.isEmpty() || !cooling.isEmpty()) {
            // if no tasks are available to schedule at current time, go idle until the first cooling task becomes available
            if(pq.isEmpty()) count = cooling.peek().lastUsed + n + 1;
            
            // Add any tasks in cooling that just became available for scheduling
            while(!cooling.isEmpty() && count > cooling.peek().lastUsed + n) {
                pq.add(cooling.poll());
            }
            
            // Schedule the most frequent occurring task by polling priority queue.
            Task t = pq.poll();
            t.lastUsed = count++;
            t.freq--;
            
            // Add the task back into cooling if there are more instances of it to schedule.
            if(t.freq != 0) cooling.add(t);
        }
        
        return count;
    }
}
 */