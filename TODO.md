# TODO:

## Tasks:

- Figure out how to write `_app.js` in Scala

## Issues:

- Currently fs is included in our bundle, which causes `Module not found: Can't resolve 'fs'`, for now I've made a band-aid fix. See https://github.com/vercel/next.js/issues/7755

- Class components don't seem to work in combination with `ModuleSplitStyle.SmallestModules` and `fastLinkJS`.