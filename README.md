<h1>Study_Of_Reflections</h1>
<p>
  <strong>About</strong><br />
  Study project focused on Java <strong>Reflections</strong> and <strong>Custom Annotations</strong>, demonstrating how to interact with objects and metadata dynamically at runtime.<br />
  It includes examples involving class analysis, constructor usage, field access, and annotation-based behavior replacement.
</p>
<h2>📌 Features</h2>
<ul>
  <li>Created and used a custom annotation <code>KeyType</code> to identify entity keys (e.g., CPF or Product Code);</li>
  <li>Used reflections to get class names, constructors, and method return types at runtime;</li>
  <li>Dynamic instantiation and data printing using reflection API;</li>
  <li>Handled exceptions when fetching annotation values with detailed Javadocs;</li>
  <li>Project based on and adapted from a Java CRUD example I previously developed.</li>
</ul>
<h2>🗂️ Project Structure</h2>
<ul>
  <li><code>br.com.eaugusto.reflection</code>: Contains reflection logic and main application <code>AppReflections</code>;</li>
  <li><code>br.com.eaugusto.model</code>: Holds the <code>Product</code> class with fields like code, description, and price;</li>
  <li><code>br.com.eaugusto.annotations</code>: Contains the <code>KeyType</code> annotation and related handling logic.</li>
</ul>
<h2>🚀 How to Run</h2>
<ol>
  <li>Clone the repository and open it in your IDE (STS, Eclipse, IntelliJ);</li>
  <li>Run <code>AppReflections.java</code> to see class and field reflection in action;</li>
  <li>Explore <code>KeyType</code> and see how it replaces logic from previous interfaces.</li>
  <li>Additionally, run <code>App.java</code> and see the CRUD in action.</li>
</ol>
<h2>📅 Commit Highlights</h2>
<h3>June 11, 2025</h3>
<ul>
  <li>Added exception handling in <code>getKey()</code> and documented it with Javadoc;</li>
  <li>Created and applied <code>KeyType</code> annotation to entities (replacing <code>getCodeOrCPF()</code>);</li>
  <li>Removed unnecessary storage logic to simplify the study focus;</li>
  <li>Copied base CRUD from a previous repository for annotation integration.</li>
</ul>
<h3>June 10, 2025</h3>
<ul>
  <li>Implemented logic to return method names and their types using reflection;</li>
  <li>Suppressed non-critical warnings specific to study context.</li>
</ul>
<h3>June 9, 2025</h3>
<ul>
  <li>Created main class <code>AppReflections</code> using Java Reflection to get class details, instantiate objects and print data;</li>
  <li>Created the <code>Product</code> class with fields: <code>code</code>, <code>description</code>, and <code>price</code>.</li>
</ul>
<h2>📚 Learning Goals</h2>
<ul>
  <li>Understand the use of Java Reflection to inspect and manipulate class structure at runtime;</li>
  <li>Learn how to create and apply custom annotations for metadata-driven logic;</li>
  <li>Replace interface logic with annotations for more flexible code design.</li>
</ul>
