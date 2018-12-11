/**
 * 业务B模块
 * https://github.com/facebook/react-native
 * @flow
 */
import '../common/index';
import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    AppRegistry,
    Image,
} from 'react-native';


export default class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>
                    这是B业务模块
                </Text>
                <Image style={styles.image} source={require('./linux.jpeg')}/>
            </View>
        );
    }
}

AppRegistry.registerComponent('subB', () => App);

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    text: {
        fontSize: 17,
        color: 'gray',
        marginTop: 20,
        borderWidth: 1,
        borderColor: 'green',
        borderRadius: 5,
        textAlign: 'center',
    },
    image: {
        width: 60,
        height: 60,
        marginTop: 20,
    }
});
