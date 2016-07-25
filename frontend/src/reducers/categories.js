import {combineReducers} from 'redux';

const FETCH = 'fetchCategories/FETCH';
const FETCH_SUCCESS = 'fetchCategories/FETCH_SUCCESS';
const FETCH_FAIL = 'fetchCategories/FETCH_FAIL';

// Reducer
const loading = (state = false, action) => {
    switch (action.type) {
        case FETCH:
            return true;
        case FETCH_SUCCESS:
            return false;
        case FETCH_FAIL:
            return false;
        default:
            return state;
    }
};
const categories = (state = null, action) => {
    switch (action.type) {
        case FETCH_SUCCESS:
            return action.result.data;
        default:
            return state;
    }
};

export default combineReducers({loading, categories});

// Actions
export const fetchCategoriesAction = () => ({
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.get('/api/categories')
});

