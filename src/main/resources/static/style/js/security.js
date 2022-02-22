window.onload = function() {

    document.getElementById("onetime").onclick = function() {
        this.disabled='disabled';
        this.setTimeout(this.disabled=false,100)
    }
}
