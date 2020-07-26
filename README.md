# rmf
Rename multiple files using your $EDITOR. Written in Java compiled by native-image.

## Install
1. Install executable from latest [release](https://github.com/strogiyotec/rmf/releases)
2. Put executable into `/usr/local/bin' directory to access it from everywhere

# Important
Starting with relase 2.0 executable was renamed from **rmf** to **mmf** 
(Move multiple files) because rmf sometimes is confused with Remove multiple files abbreviation

# Example

This is how it works.
1. You send names of all files you want to rename to rmf(rename multiple files).
2. Then **rmf** will open your $EDITOR(vim if value is not set) with file names.
3. Change names and save the file (:wq in vim)
4. Your files are renamed 

# Demo
![demo](https://raw.githubusercontent.com/strogiyotec/rmf/master/statics/example.gif)

As you can see three files (b1,b2,b3) were renamed
