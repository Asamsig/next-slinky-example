const {PHASE_DEVELOPMENT_SERVER} = require('next/constants')

// module.exports = (phase, {defaultConfig}) => {
//     if (phase === PHASE_DEVELOPMENT_SERVER) {
//         return {
//             /* development only config options here */
//         }
//     }
//
//     return {
//         /* config options for all phases except development here */
//     }
// }

module.exports = {
    webpack: (config, {isServer}) => {
        // Fixes npm packages that depend on `fs` module
        if (!isServer) {
            config.node = {
                fs: 'empty'
            }
        }

        return config
    }
}