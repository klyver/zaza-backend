import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchProductAction, updateProductAction} from '../../reducers/products';
import {fetchProductOptionsAction} from '../../reducers/productOptions';
import {fetchCategoriesAction} from '../../reducers/categories';
import {Link} from 'react-router';
import ProductEditor from '../components/ProductEditor';

const ProductEditPage = class extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.fetchProductAction(this.props.params.productId);
        this.props.fetchProductOptionsAction();
        this.props.fetchCategoriesAction();
    }

    render() {
        if (this.props.loading) {
            return (<h1>Loading...</h1>)
        }

        this.state = {
            product: this.props.products[this.props.params.productId]
        };

        return (
            <div>
                <h2>Edit Product</h2>
                <ProductEditor
                    product={this.state.product}
                    productOptions={this.props.productOptions}
                    categories={this.props.categories}
                    updateProduct={(product) => {
                        this.setState({
                            product: product
                        });
                    }}
                    admin={this.props.admin}
                    errorMessage={this.props.errorMessage}
                />
                <button
                    onClick={ (e) => {
                        e.preventDefault();
                        this.props.updateProductAction(this.state.product);
                    }}
                    style={{marginTop: '30px'}}
                >
                    Update
                </button>
            </div>
        );
    }
};

export default connect(
    state => ({
        products: state.products.data,
        loading:  state.products.loading || !state.categories || !state.productOptions,
        productOptions: state.productOptions,
        categories: state.categories,
        admin: state.authentication.admin,
        errorMessage: state.products.errorMessage
    }),
    {
        fetchProductAction,
        fetchProductOptionsAction,
        fetchCategoriesAction,
        updateProductAction
    }
)(ProductEditPage);

