# Bonus Round

1. In what situations is a quoted expression equal to that same
   textual expression without the quote? A first example:

    ```clojure
    (= (quote 123) 123)
    ```

   What compound data structures can go in the `123` spot there, and which can't?

2. Why doesn't the `(println never-happens)` line below blow up with an "Unable
   to resolve symbol" exception?

    ```clojure
    (if false
      (def never-happens 1)
      (def always-happens 1))

    (println always-happens)
    (println never-happens)
    ```

   See [http://www.gfredericks.com/speaking/2015-02-25-vars.pdf]() for a deep
   dive into vars.

3. With an AOT-compiled namespace, when are macros expanded? During AOT
   compilation? When the namespace is loaded? Make an initial guess at the
   answer (out loud or typed/written out!); then open
   `src/macro_workshop/aot_example.clj` and run
   `lein run -m macro-workshop.aot-example` a couple of times, and make sure
   you understand why you see what you see.

   Try `lein run -m macro-workshop.aot-example --expand` as well!

4. What are the implications of the previous answer for AOT-compiled projects?
   That is, what characteristics should macros have in order to avoid bugs in
   AOT-compiled code?

5. It's normally useful to think of macros as being completely gone at runtime.
   Does this mean your running program can't access macros? (hint: no) Name 3
   ways it can access/use macros. No judgment here: be as evil as you like!

6. We know that we can write expressions in the verb position that evaluate to
   functions. What happens if we put a macro expression in the verb position
   and try to make it expand to a special form symbol?

   ```clojure
   (defmacro make-symbol [x] (symbol x))
   ((make-symbol "if") false (println "the if check didn't work right"))
   ```

   What if we made it expand to a macro symbol instead of a special form?

7. There are two rarely-used available bits of syntax in macros: `&form` and
   `&env`. Take a look at how they work:

    ```clojure
    (defmacro show-special-stuff [& args]
      (println "macroexpand-time, &env: " (pr-str &env))
      (println "macroexpand-time, &form: " (pr-str &form)))

    (show-special-stuff 1 2 3)

    (let [x 1 y 2]
      (show-special-stuff 1 2 3))
    ```

   So `&form` gives us the full expression that is being expanded (useful for
   things like testing libraries that want to be able to show users where they
   went wrong). And `&env` gives access to local variable bindings. Using lists
   and quoting (no need to look into the compiler source code for this!), make
   the tests pass in `spec/macro_workshop/secret_magic_spec.clj`.


    ```bash
    lein spec -a spec/macro_workshop/secret_magic_spec.clj
    ```
