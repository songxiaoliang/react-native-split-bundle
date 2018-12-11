import '../Common';
import React, { Component } from 'react';
import {
    Text,
    View,
    Image,
    StyleSheet,
    AppRegistry,
    TouchableOpacity
} from 'react-native';

export default class FirstModule extends Component {

    render() {
        return (
            <View style={ styles.container }>
                <Text>
                    RN业务模块1
                </Text>
                <Image 
                    style={ styles.icon } 
                    source={ require('../../images/icon_1.png') } />
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

AppRegistry.registerComponent('FirstModule', () => FirstModule);
