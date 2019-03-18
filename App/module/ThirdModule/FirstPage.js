import '../Common';
import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet
} from 'react-native';

export default class ThirdModule extends Component {

    static navigationOptions = ({ navigation }) => ({
        headerTitle: '第一个界面',
        gesturesEnabled: true,
    });

    /**
     * 界面跳转
     */
    goToScene() {
        this.props.navigation.push('SecnodPage');
    }

    render() {
        return(
            <View style={styles.container}>
                <Text style={styles.text} onPress={()=>this.goToScene()}>
                    我是第三个Module的第一个界面, 点击我跳转到下一个
                </Text>
            </View>
        )
    }
}

const styles = StyleSheet.create({

    container: {
        flex: 1
    },

    text: {
        color: '#ff0ff0',
        fontSize: 16,
        alignSelf: 'center'
    }
})
