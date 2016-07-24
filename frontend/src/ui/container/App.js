import React, {Component} from 'react';
import NavLink from '../components/NavLink';
import {connect} from 'react-redux';
import {getSession} from '../../reducers/authentication';

const TopMenu = ({roles, username, admin}) => {
    const menuItems = [
        {label: 'Products', link: '/products'},
        {label: 'Orders', link: '/orders'}
    ];
    if (admin) {
        menuItems.push({label: 'Users', link: '/users'});
        menuItems.push({label: 'ProductOptions', link: '/productoptions'});
    }

    return (
        <div>
            <div>User: <b>{username}</b> - Roles: {roles.join()} - <NavLink to="/logout">Logout</NavLink></div>
            <ul>
                {menuItems.map((item, key) => (
                    <li key={key}>
                        <NavLink to={item.link}>{item.label}</NavLink>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export class App extends Component {

    componentDidMount() {
        this.props.getSession();
    }

    render() {
        return (
            <div id="application">
                {this.props.roles.length > 0 ? <TopMenu roles={this.props.roles} username={this.props.username} admin={this.props.admin}/> : null}
                {this.props.children}
            </div>
        );
    }
}

export default connect(
    state => ({
        username: state.authentication.username,
        roles: state.authentication.roles,
        admin: state.authentication.admin
    }),
    {getSession}
)(App);
