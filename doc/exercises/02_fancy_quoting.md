# Fancy quoting

Sure, I know the real name is syntax-quote. But isn't "fancy-quote" more fun?

1. Have another look at your solutions to the second code generation exercise,
   the one where you wrote macros. Do they look too verbose now?

   Using what you know about syntax quoting, update those examples to make the
   macro code look more like the code it's generating.

2. There are some failing tests in `spec/macro_workshop/fancy_quoting_spec.clj`
   — make the first `describe` block pass! Just implement `math-operations-set`
   for now. Use your new syntax-quoting knowledge to generate code in a concise
   way.

   ```bash
   lein spec -a spec/macro_workshop/fancy_quoting_spec.clj
   ```

   Just the first `describe` block for now! (If you go too far that's OK too.)

3. Take a look at the source code for the `if-not` macro and convince yourself
   that you would have written it, were it not (a) already written, and (b)
   confusing for many programmers to read & maintain (see
   [https://signalvnoise.com/posts/2699-making-sense-with-rubys-unless]() for
   an example from the Ruby world).

    ```clojure
    (defmacro if-not
      "Evaluates test. If logical false, evaluates and returns then expr,
      otherwise else expr, if supplied, else nil."
      {:added "1.0"}
      ([test then] `(if-not ~test ~then nil))
      ([test then else]
       `(if (not ~test) ~then ~else)))
    ```

4. One interesting thing about `if-not` is that there are 2 arities: a
   2-argument version and a 3-argument version. Implement a multiple-arity
   macro to pass the remainder of the tests in
   `src/macro_workshop/fancy_quoting_spec.clj`.

   Again, you don't need a macro here, but the practice is worthwhile.

