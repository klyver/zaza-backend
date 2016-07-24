import React, {Component} from 'react';
import {connect} from 'react-redux';
import LoginPage from './LoginPage';

export default connect(
    state => ({
        roles: state.authentication.roles,
        username: state.authentication.username
    })
)(({roles, username}) => (
    <div>
        {roles.length > 0 ? <h1>Welcome {username}</h1> : <LoginPage/> }
    </div>
));


