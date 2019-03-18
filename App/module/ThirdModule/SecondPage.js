import '../Common';
import React, { Component } from 'react';
import {
    View,
    Text,
    StyleSheet
} from 'react-native';

export default class ThirdModule extends Component {

    static navigationOptions = ({ navigation }) => ({
        headerTitle: '第二个界面',
        gesturesEnabled: true,
    });

    /**
     * 界面跳转
     */
    goToScene() {
        this.props.navigation.goBack();
    }

    render() {
        return(
            <View style={styles.container}>
                <Text style={styles.text} onPress={()=>this.goToScene()}>
                    欢迎来到第二页
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
