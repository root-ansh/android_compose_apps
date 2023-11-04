# 1. learnings with Compose Hands-On : Calculator

theory : https://docs.google.com/document/d/14klrHe9FePGQGxMEXjprHsXfeiJJHv61banQ-ReVCxA/edit

1. your usual logics for view weight(used in linear layout) and view aspect ratio (used in constraint view) can be applied here
2. pass a modifier to every component, so that the parent could provide additional modifications wrt itself.
3. in view, if a layout is a part of linear layout, then app:weight attribute would get available in its scope. this is applicable here too, but only if a composite view allows passing modifier in itself
4. modifiers are the backbone of all ui design. understand them well enough
5. there are new ways of setting space : arrangement modifier, in addition to margin/padding/gravity etc
6. we are using a basic mvc architecture with activity acting as the controller,and the component getting its data and updating its state via liveData and updated via setContent. its very much like a react component
7. this app does a terrible role of handling rotation
8. modifiers we used : `<todo>`

# 2. learnings with Compose Hands-On : Checklist
1. It is incredibly easy to create a list with compose
2. spinner, radio button  or any other one-from-many choice based  components are pain in the ass and nto available natively
3. remember api: very interesting but could not get it to work. mutable livedata ftw
4. clip and border modifier does similar things, but sometimes both are needed. mostly border is only needed.

# 3. learnings with Compose Hands-On : Main Activity chooser
1. `activity.setContent{...}` does not maintain history

