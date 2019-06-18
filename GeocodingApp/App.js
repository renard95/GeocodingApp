import React, { Component } from  'react';
import { AppRegistry, StyleSheet, Button, View, Text } from 'react-native';
import { createStackNavigator, createAppContainer } from 'react-navigation'; // Version can be specified in package.json
import MapView, { PROVIDER_GOOGLE } from  'react-native-maps';
import {Picker} from 'react-native';

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
          onPress={() => this.props.navigation.navigate('Train')}
        />
        </View>
        <View style={[{ width: "90%", margin: 10 }]}>
        <Button
          title="Bus Oth"
          onPress={() => this.props.navigation.navigate('BusOth')}
        />
        </View>
        <View style={[{ width: "90%", margin: 10 }]}>
        <Button
          title="Bus Muni"
          onPress={() => this.props.navigation.navigate('BusMuni')}
        />
        </View>


      </View>

    );
  }
}

class TrainScreen extends React.Component {
  static navigationOptions = {
    title: 'Train Page',
    headerStyle: {
      backgroundColor: '#00bfff',
    },
    headerTintColor: '#fff',
    headerTitleStyle: {
      fontWeight: 'bold',
    },
  };
  state = {user: ''}
updateUser = (user) => {
   this.setState({ user: user })
}
  render() {
    return (
      <View>

      <MapView
       provider={ PROVIDER_GOOGLE }
       style = {styles.map}
           initialRegion = {{
               latitude: 13.139238380834923,
               longitude: 80.25188422300266,
               latitudeDelta: 0.0922,
               longitudeDelta: 0.0421,
               }}
           >
         <MapView.Marker
           coordinate={{latitude: 37.78825,
           longitude: -122.4324}}
           title={"title"}
           description={"description"}
        />
     </MapView>
       <View style={{ flex: 1, flexDirection: 'column', alignItems: 'center', justifyContent: 'space-between' }}>
       <View style={[{ width: "90%", margin: 10 }]}>
     <Button
  title="Validate"
  color="#008000"
/>
   </View>

<View style={[{ width: "90%", margin: 10 }]}>
<Button
  title="Signal error"
  color="#ff0000"
/>
            <Text>Id actual stop:</Text>
            <Picker selectedValue = {this.state.user} onValueChange = {this.updateUser}>
               <Picker.Item label = "Steve" value = "steve" />
               <Picker.Item label = "Ellen" value = "ellen" />
               <Picker.Item label = "Maria" value = "maria" />
            </Picker>
            <Button
              title="Select Id"
            />
   </View>
           </View>


              </View>

    );
  }
}


class BusMuniScreen extends React.Component {
  static navigationOptions = {
    title: 'BusMuni Page',
    headerStyle: {
      backgroundColor: '#00bfff',
    },
    headerTintColor: '#fff',
    headerTitleStyle: {
      fontWeight: 'bold',
    },
  };
  state = {user: ''}
updateUser = (user) => {
   this.setState({ user: user })
}
  render() {
    return (
      <View>
      <MapView
       provider={ PROVIDER_GOOGLE }
       style = {styles.map}
           initialRegion = {{
               latitude: 13.139238380834923,
               longitude: 80.25188422300266,
               latitudeDelta: 0.0922,
               longitudeDelta: 0.0421,
               }}
           >
         <MapView.Marker
           coordinate={{latitude: 37.78825,
           longitude: -122.4324}}
           title={"title"}
           description={"description"}
        />
     </MapView>
     <View style={{ flex: 1, flexDirection: 'column', alignItems: 'center', justifyContent: 'space-between' }}>
     <View style={[{ width: "90%", margin: 10 }]}>
   <Button
title="Validate"
color="#008000"
/>
 </View>

<View style={[{ width: "90%", margin: 10 }]}>
<Button
title="Signal error"
color="#ff0000"
/>
          <Text>Id actual stop:</Text>
          <Picker selectedValue = {this.state.user} onValueChange = {this.updateUser}>
             <Picker.Item label = "Steve" value = "steve" />
             <Picker.Item label = "Ellen" value = "ellen" />
             <Picker.Item label = "Maria" value = "maria" />
          </Picker>
          <Button
            title="Select Id"
          />
 </View>
         </View>

           </View>
    );
  }
}
class BusOthScreen extends React.Component {
  static navigationOptions = {
    title: 'BusOth Page',
    headerStyle: {
      backgroundColor: '#00bfff',
    },
    headerTintColor: '#fff',
    headerTitleStyle: {
      fontWeight: 'bold',
    },
  };
  state = {user: ''}
updateUser = (user) => {
   this.setState({ user: user })
}
  render() {
    return (
      <View>
      <MapView
       provider={ PROVIDER_GOOGLE }
       style = {styles.map}
           initialRegion = {{
               latitude: 13.139238380834923,
               longitude: 80.25188422300266,
               latitudeDelta: 0.0922,
               longitudeDelta: 0.0421,
               }}
           >
         <MapView.Marker
           coordinate={{latitude: 37.78825,
           longitude: -122.4324}}
           title={"title"}
           description={"description"}
        />
     </MapView>
     <View style={{ flex: 1, flexDirection: 'column', alignItems: 'center', justifyContent: 'space-between' }}>
     <View style={[{ width: "90%", margin: 10 }]}>
   <Button
title="Validate"
color="#008000"
/>
 </View>

<View style={[{ width: "90%", margin: 10 }]}>
<Button
title="Signal error"
color="#ff0000"
/>
          <Text>Id actual stop:</Text>
          <Picker selectedValue = {this.state.user} onValueChange = {this.updateUser}>
             <Picker.Item label = "Steve" value = "steve" />
             <Picker.Item label = "Ellen" value = "ellen" />
             <Picker.Item label = "Maria" value = "maria" />
          </Picker>
          <Button
            title="Select Id"
          />
 </View>
         </View>

           </View>
    );
  }
}

const RootStack = createStackNavigator(
  {
    Home: HomeScreen,
    Train: TrainScreen,
    BusMuni: BusMuniScreen,
    BusOth: BusOthScreen,
  },
  {
    initialRouteName: 'Home',
  }
);


const styles = StyleSheet.create({
    map: {
        height: '70%',
        width: '100%',
        }
});


const AppContainer = createAppContainer(RootStack);

export default class App extends React.Component {
  render() {
    return <AppContainer />;
  }
}
