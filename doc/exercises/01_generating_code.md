# Generating code

## Generating expressions with plain old functions

1. In the REPL, write an expression that will *return* the expression `(println
   "hi")`.  To be clear, the code that you *type* shouldn't print "hi" to
   STDOUT, but if you copy the return value and type *that* into the REPL too,
   that *should* print "hi" to STDOUT.

   (hint: what does `quote` do?)

   What is the type of the return value?


2. Congratulations! You've just generated some code! Now let's do something
   more fun. In `spec/macro_workshop/code_generation_spec.clj`, there are some
   failing tests. Start up the test auto-runner with:

    ```bash
    lein spec -a spec/macro_workshop/code_generation_spec.clj
    ```


   Now make each test pass, by first removing the `(pending)` marker, and then
   fixing the code in `src/macro_workshop/code_generation.clj`. The auto-runner
   will re-run the tests every time you save one of the two relevant files. I
   like to keep one window open with the auto-runner, and one with my editor,
   so that I can see both at once.

   Whether things are passing or failing, feel very free to write additional
   tests and/or use the REPL to make sure you understand why!


3. Read the source code for `clojure.core/when`, and explain it [out loud!] to
   your pair or the person next to you:

    ```clojure
    (defmacro when
      "Evaluates test. If logical true, evaluates body in an implicit do."
      {:added "1.0"}
      [test & body]
      (list 'if test (cons 'do body)))
    ```


4. Now macroexpand some input given to `when` at the REPL, and see if your
   explanation holds up:

    ```bash
    user=> (macroexpand '(when (= 1 2) (println "Hi!") (println "Bye!")))
    ```

5. There are some failing tests in
   `spec/macro_workshop/code_generation_2_spec.clj`. Make them pass! You
   already have the code you need for each example (from the previous exercise)
   - now you should be able to literally copy/paste that code!

   The rules here are: you have to use macros for every example in this
   exercise.  You totally shouldn't use macros for these examples in real
   life...  we'll see why soon enough.

   ```bash
   lein spec -a spec/macro_workshop/code_generation_2_spec.clj
   ```
