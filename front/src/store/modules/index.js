import main from './main.js';

// const requireModule = require.context('.', false, /\.js$/);
const modules = { main };

// requireModule.keys().forEach(fileName => {
//     if (fileName === './index.js') return;
//     const moduleName = fileName.replace(/(\.\/|\.js)/g, '');
//     module[moduleName] = requireModule(fileName).default;
// });

export default modules;
