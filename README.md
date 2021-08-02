# let-error-demo

[Typed Clojure](https://github.com/typedclojure/typedclojure) 1.0.17 introduces custom type error messages for macros.

For example, the following code yields a type error--because you can't destructure a set:

```clojure
(let [[a] #{:foo}]
  a)
```

Previously, Typed Clojure would expand this code and type check its expansion. The resulting error message is too low-level and quite difficult to decipher, especially in you are unfamiliar with `destructure`'s implementation:

```clojure
$ lein with-profile +old-typed typed check
...
Type Error (file:/Users/ambrose/Projects/scratch/let-error-demo/src/let_error_demo/core.clj:6:3)
Polymorphic static method clojure.lang.RT/nth could not be applied to arguments:
Polymorphic Variables:
        x
        y

Domains:
        (t/U (t/I Sequential (Seqable x) (t/CountRange 1)) (t/I (Indexed x) (t/CountRange 1))) (t/Val 0) t/Any
        (t/U (Indexed x) nil (t/I Sequential (Seqable x))) t/Int y

Arguments:
        (t/HSet #{:foo}) (t/Val 0) nil

Ranges:
        x
        (t/U x y)

in:
(clojure.lang.RT/nth vec__30544 0 nil)
...
```

The new approach--introduced in 1.0.17--uses a custom typing rule for `let` to inject surface syntax into the error message:

```clojure
$ lein typed check
...
Type Error (file:/Users/ambrose/Projects/scratch/let-error-demo/src/let_error_demo/core.clj:6:3)
The type `(t/HSet #{:foo})` cannot be destructured via syntax `[a]` because the type cannot be passed as the first argument of `nth`.`

in:
(let [[a] #{:foo}] a)
...
```

Notice the entire `let` form is in the message, as well as the involved type and destructuring syntax.

## License

Copyright © 2021 Ambrose Bonnaire-Sergeant

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
