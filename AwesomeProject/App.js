import React, { Component, PropTypes }from 'react';
import { Button, View, Text, Linking, StyleSheet} from 'react-native';
import { createAppContainer, createStackNavigator } from 'react-navigation'; // Version can be specified in package.json

class HomeScreen extends React.Component {
  static navigationOptions = {
    title: 'Main Page',
    headerStyle: {
      backgroundColor: '#00bfff',
    },
    headerTintColor: '#fff',
    headerTitleStyle: {
      fontWeight: 'bold',
    },
  };
  render() {
    return (

      <View style={{ flex: 1, flexDirection: 'column', alignItems: 'center', justifyContent: 'space-around' }}>
        <View style={[{ width: "90%", margin: 10 }]}>
        <Button
          title="Train"
            onPress={() => Linking.openURL('http://gisak.vsb.cz/stops/projet/train.html')}
        />
        </View>
        <View style={[{ width: "90%", margin: 10 }]}>
        <Button
          title="Bus Oth"
            onPress={() => Linking.openURL('http://gisak.vsb.cz/stops/projet/bus_oth.html')}
        />
        </View>
        <View style={[{ width: "90%", margin: 10 }]}>
        <Button
          title="Bus Muni"
            onPress={() => Linking.openURL('http://gisak.vsb.cz/stops/projet/bus_muni.html')}
        />
        </View>


      </View>

    );
  }
}



const RootStack = createStackNavigator(
  {
    Home: {
      screen: HomeScreen,
    },
  },
  {
    initialRouteName: 'Home',
  }
);

const AppContainer = createAppContainer(RootStack);

export default class App extends React.Component {
  render() {
    return <AppContainer />;
  }
}
