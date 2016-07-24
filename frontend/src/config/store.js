import React from 'react';
import reducer from '../reducers/index';
// import {createStore, applyMiddleware, compose} from 'redux';
import {createStore, applyMiddleware} from 'redux';
import promiseMiddleware from './promiseMiddleware';

// const middlewares = [applyMiddleware(promiseMiddleware)];
const middlewares = [promiseMiddleware];

export default () => {
    const initialState = {};
    // const store = createStore(reducer, initialState, compose(...middlewares));
    const store = createStore(reducer, initialState, applyMiddleware(...middlewares));

    // if (module.hot) {
    //     // Enable Webpack hot module replacement for reducers
    //     module.hot.accept('../reducers', () => {
    //         const nextReducer = require('../reducers');
    //         store.replaceReducer(nextReducer);
    //     });
    // }
    return store;
};



