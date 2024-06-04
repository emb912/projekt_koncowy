import * as React from 'react';
import logo from './house_black.svg';
import { request, setAuthHeader } from '../../axios_helper';
import '../../styles/Navbar.css';
import '../../styles/Buttons.css';

export default class Navbar extends React.Component {

    logout = () => {
        this.props.onLogout();
        setAuthHeader(null);
    };


    render() {
        const { isLoggedIn } = this.props;

        return (
            <nav class="navbar navbar-expand-lg navbar-light bg-light" >
                <div className="container-fluid">
                    <a className="navbar-brand" href="/">
                        <img src={logo} alt="" width="30" height="24" className="d-inline-block align-text-top" />
                    </a>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">

                            <li className="nav-item">
                                <a className="nav-link active" href="/data">Ceny mieszkań</a>
                            </li>
                        </ul>
                        <div className="d-flex">
                            {console.log(isLoggedIn)}
                            {!isLoggedIn ? (
                                <a href='/login'>
                                    <button className="btn blue-btn">
                                        Uzyskaj dostęp
                                    </button>
                                </a>

                            ) : (
                                <a href='/'>
                                    <button className="btn btn-dark" onClick={this.logout}>
                                        Wyloguj się
                                    </button>
                                </a>
                            )}
                        </div>
                    </div>
                </div>
                <p></p>
            </nav>


        )
    }
}