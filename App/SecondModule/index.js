import '../common';
import React, { Component } from 'react';
import {
    Text,
    View,
    Image,
    StyleSheet,
    AppRegistry,
    TouchableOpacity
} from 'react-native';

export default class SecondModule extends Component {

    render() {
        return (
            <View style={ styles.container }>
                <Text>
                    模块2
                </Text>
                <Image style={ styles.icon } source={ require('../images/snowman.png') } />
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center'
    },

    icon: {
        marginTop: 16
    }
});

AppRegistry.registerComponent('SecondModule', () => SecondModule);
