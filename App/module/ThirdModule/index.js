import '../Common';
import {
    AppRegistry
} from 'react-native';
import { createStackNavigator } from 'react-navigation';

import FirstPage from './FirstPage';
import SecondPage from './SecondPage';

const AppRouter = createStackNavigator(
    {
        FirstPage: { screen: FirstPage },
        SecnodPage: { screen: SecondPage },
    },
    {
        navigationOptions: {
          swipeEnabled: true,
          animationEnabled: true,
          lazy: true,
        },
    }
);

export default AppRouter;

AppRegistry.registerComponent('ThirdModule', () => AppRouter);