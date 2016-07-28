import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchProductsAction} from '../../reducers/products';
import NavLink from '../components/NavLink';

const ProductsPage = class extends Component {

    componentDidMount() {
        this.props.fetchProductsAction();
    }

    render() {
        if (this.props.errorMessage) {
            return <div style={{color: 'RED'}}>{this.props.errorMessage}</div>
        } else if (this.props.loading) {
            return <h1>Loading...</h1>
        }

        const products = [];
        for(const p in this.props.products) {
            products.push(this.props.products[p]);
        }

        return (
            <div>
                {
                    this.props.manufacturer ? <NavLink to={'/products/new'}>New product</NavLink> : null
                }
                <h2>Products:</h2>
                <table>
                    <thead>
                        <tr>
                            {this.props.admin ? <th>manufacturer</th> : null}
                            <th>name</th>
                            <th>sourceId</th>
                            <th>approved</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    {
                        products.map((product, index) => (
                        <tr
                            key={index}
                        >
                            {this.props.admin ? <td>{product.manufacturer}</td> : null}
                            <td>{product.name}</td >
                            <td>{product.sourceId}</td>
                            <td>{product.approved ? 'YES' : 'NO'}</td>
                            <td>
                                <NavLink to={`/products/${product.id}`}>Edit</NavLink>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>

            </div>
        );
    }
};

export default connect(
    state => ({
        loading: state.products.loading,
        products: state.products.data,
        admin: state.authentication.admin,
        manufacturer: state.authentication.manufacturer
    }),
    {fetchProductsAction}
)(ProductsPage);

