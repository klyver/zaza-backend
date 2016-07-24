import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchProductList} from '../../reducers/productList';
import NavLink from '../components/NavLink';

const ProductsPage = class extends Component {

    componentDidMount() {
        this.props.fetchProductList();
    }

    render() {
        if (this.props.loading) {
            return <h1>Loading...</h1>
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
                    {this.props.products.map((product, index) => (
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
        products: state.productList.products,
        loading: state.productList.loading,
        admin: state.authentication.admin,
        manufacturer: state.authentication.manufacturer
    }),
    {fetchProductList}
)(ProductsPage);

