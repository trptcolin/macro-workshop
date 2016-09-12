# Symbol Capture

1. Look back at each of your solutions so far. Where is there potential for
   symbol capture? Do you ever emit symbols that are neither
   namespace-qualified nor gensyms? Take a close look and fix any problems you
   find.

   Feel free to write some additional regression tests that expose the
   problems, before you fix them.

2. Clojure itself has several macros defined using non-namespace symbols.
   Search for `defmacro` on
   https://github.com/clojure/clojure/blob/master/src/clj/clojure/core.clj (or
   with your editor/IDE if you can navigate to clojure.core's source code) to
   find some macros that use plain old quoted symbols. Are these problematic in
   terms of symbol capture? Why or why not?

3. You've already learned all the skills you need in order to build macros. Now
   let's put it together. There are some failing tests in
   `spec/macro_workshop/if_nonempty_let_spec.clj`. Make them pass.

   ```bash
   lein spec -a spec/macro_workshop/if_nonempty_let_spec.clj
   ```

4. Now that you have the happy path working for the macro in #3, let's provide
   some nice error messages for our users. There are some failing tests for you
   in `macro-workshop.user-friendliness-spec`.

   Once you get these passing, be sure to try the `NOTE` at the top of that
   file, and answer the question there.


