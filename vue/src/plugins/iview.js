import Vue from 'vue'
import { Button, Form, FormItem, Input, Icon,Card, Layout, Content,
    Header, Menu, MenuItem, Breadcrumb, Sider, Submenu, Footer, 
    BreadcrumbItem, Switch, Checkbox, Slider, Radio, RadioGroup, DatePicker,
    TimePicker, Select, Option, CheckboxGroup, Row, Col, Notice } from 'view-design'
import 'view-design/dist/styles/iview.css'

Vue.component('Form',Form)
Vue.component('FormItem',FormItem)
Vue.component('Input',Input)
Vue.component('Icon',Icon)
Vue.component('Card',Card)
Vue.component('Layout',Layout)
Vue.component('Content',Content)
Vue.component('Header',Header)
Vue.component('Menu',Menu)
Vue.component('MenuItem',MenuItem)
Vue.component('Breadcrumb',Breadcrumb)
Vue.component('Sider',Sider)
Vue.component('Submenu',Submenu)
Vue.component('Footer',Footer)
Vue.component('BreadcrumbItem',BreadcrumbItem)
// Vue.component('i-switch',Switch)
Vue.component('Checkbox',Checkbox)
// Vue.component('Slider',Slider)
Vue.component('Radio',Radio)
Vue.component('RadioGroup',RadioGroup)
Vue.component('DatePicker',DatePicker)
Vue.component('TimePicker',TimePicker)
Vue.component('Select',Select)
Vue.component('Option',Option)
Vue.component('CheckboxGroup',CheckboxGroup)
Vue.component('Row',Row)
Vue.component('Col',Col)
Vue.component('Button', Button)
Vue.component('Notice', Notice)

Vue.prototype.$Notice = Notice

