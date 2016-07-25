import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchProduct} from '../../reducers/product';
import {fetchProductOptionList} from '../../reducers/productOptionList';
import {updateProductAction} from '../../reducers/updatingProduct';
import {fetchCategoriesAction} from '../../reducers/categories';
import {Link} from 'react-router';
import ProductEditor from '../components/ProductEditor';

const ProductEditPage = class extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.fetchProduct(this.props.params.productId);
        this.props.fetchProductOptionList();
        this.props.fetchCategoriesAction();
    }

    render() {
        this.state = {
            product: this.props.product
        };

        if (this.props.updatingProduct) {
            return <h1>Updating product</h1>
        } else if (this.props.loading || !this.state.product) {
            return <h1>Loading...</h1>
        }

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
                <button onClick={ (e) => {
                    e.preventDefault();
                    this.props.updateProductAction(this.state.product);
                }}
                >Update</button>
            </div>
        );
    }
};

export default connect(
    state => ({
        product: state.product.product,
        loading: state.product.loading || state.productOptionList.loading || state.categories.loading,
        productOptions: state.productOptionList.productOptions,
        categories: state.categories.categories,
        admin: state.authentication.admin,
        updatingProduct: state.updatingProduct.loading,
        errorMessage: state.updatingProduct.errorMessage
    }),
    {
        fetchProduct,
        fetchProductOptionList,
        fetchCategoriesAction,
        updateProductAction
    }
)(ProductEditPage);

