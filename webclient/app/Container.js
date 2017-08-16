import React from 'react';
import 'whatwg-fetch';

class Container extends React.Component {


  constructor() {
    super(...arguments);
    this.state = {
      title: '리액트 테스트'
    }
  }


  login() {
    console.log('로그인 클릭');
    console.log(this.state.title);



    fetch('');
  }


  render() {

    return (
      <ul>
        <li><a href="javascript:;" onClick={this.login.bind(this)}>로그인</a></li>
      </ul>
    )
  }

}


export default Container;