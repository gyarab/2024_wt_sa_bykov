async function OnLoad(activePage) {
    let file = new XMLHttpRequest();
    file.open('GET', 'menu.html', true);
    file.onload = (req) => {
        let content = req.target.responseText;
        if(content.includes("SSGSPLIT")) {
            let carr = content.split("SSGSPLIT");
            if(carr.length > 3) {
                console.error("Too many SSGSPLITs");
            }
            contentExport = carr[1];
        }
        else {
            contentExport = content;
        }
        document.querySelectorAll(".ssg-fillin-navbar").forEach((target) => { 
            target.innerHTML = content;
            target.querySelectorAll("#ssg-page-name-"+activePage).forEach((active) => {
                active.classList.add("active"); 
            });
        });
    };
    file.send();
}