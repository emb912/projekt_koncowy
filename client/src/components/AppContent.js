
import * as React from 'react';
import WelcomeContent from './WelcomeContent';
import LoginForm from './auth/LoginForm';


export default class AppContent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome"
        }
    };

    render() {
        const { componentToShow } = this.state;

        return (
            <>
                {this.state.componentToShow === "welcome" && <WelcomeContent />}
                {this.state.componentToShow === "loginform" && <LoginForm />}
            </>
        );
    };
}