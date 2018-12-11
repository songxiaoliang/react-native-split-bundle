import React, {Component} from 'react';
import {
    AppRegistry,
    Text,
    View,
} from 'react-native';
import App from './common/common';

export class Index extends Component {
    constructor(props) {
        super(props);
    }

    render() {
       return(
           <View style={{flex: 1, alignItems: 'center', justifyContent: 'center', padding: 20}}>
               <Text>注：多业务bundle在同一android.module，当然也可以是独立android.module，同等道理，核心是多bundle加载，不影响改造</Text>
           </View>
       );
    }
}

AppRegistry.registerComponent('split', () => Index);
