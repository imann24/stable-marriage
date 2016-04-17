# stable-marriage
Python and Java implementations of the Stable Marriage Problem (adapted from C++ code by Samia Qader)

<i>Code written by Isaiah Mann, Zoe Kendall, and Kaylynn Crawford</i>

##Instructions for Running:

The Java and Python Stable Marriage algorithms are functionally indentical. They can either be run with input from a file or input from the command line. To promote brevity in command line input, the program will not accept a greater number of couples than five. However, it can run on arbitrarily large files. To generate input files we wrote an additional Java script : GenerateStableMarriageProblem.java.

After they've received input (either from a text file or the command line), the programs will prompt you to choose either the men or women to propose. This is proof that the choice of proposers influences the results, and with closer analysis, it can be seen that the algorithm favors the proposers.
###To run the Python Code:
<pre><code>
python stable.py
</pre></code>
or
<pre><code>
python stable.py [file name]
</pre></code>
###To run the Java Code:
Compile:
<pre><code>
javac Stable.java
</pre></code>
then:
<pre><code>
javac Stable
</pre></code>
or
<pre><code>
java Stable [file name]
</pre></code>

###To generate text files:
<i><b>Warning:</b> if a file already exists with the same name at this directory level, the program will overwrite it</i>
<pre><code>
javac GenerateStableMarriageProblem.java
java GenerateStableMarriageProblem [file name] [number of couples]
</pre></code>
