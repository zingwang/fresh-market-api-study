//과일 검색
async function getFruit(searchInput, url) {
    let token;
    try {
        //과일 토큰획득
        const response = await axios.get('/v1/product/token/fruit')
            .then(response => {
                console.log(response.data.data.accessToken);
                token = response.data.data.accessToken;
            })
            .catch(error => {
                console.error(error);
            });
        console.log(response);
        const config = {
            method: "get",
            url: url,
            params: {
                name: searchInput
            },
            headers: {
                "Authorization": token
            }
        };
        // 과일 검색
        await axios(config)
            .then(function (response) {
                console.log(response.data); // 응답 데이터 출력
                let price = document.getElementsByClassName("price")[0];
                price.innerText = response.data.data.price;
            })
            .catch(function (error) {
                console.log(error);
            });

    } catch (error) {
        console.error(error);
    }
}

//채소 검색
async function getVegetable(searchInput, url) {
    let token;
    try {
        // 채소 토큰 획득
        const response = await axios.get('/v1/product/token/vegetable')
            .then(response => {
                console.log(document.cookie.replace('Authorization=', ''));
                token = document.cookie.replace('Authorization=', '');
            })
            .catch(error => {
                console.error(error);
            });
        console.log(response);

        const config = {
            method: "get",
            url: url,
            params: {
                name: searchInput
            },
            headers: {
                "Authorization": token
            }
        };

        // 채소 검색
        await axios(config)
            .then(function (response) {
                console.log(response.data); // 응답 데이터 출력
                let price = document.getElementsByClassName("price")[0];
                price.innerText = response.data.data.price + "원";
            })
            .catch(function (error) {
                console.log(error);
            });
    } catch (error) {
        console.error(error);
    }
}


function search() {
    // 검색어 입력 폼에서 입력한 값을 가져옴
    const category = document.getElementById("category").value;
    const searchInput = document.getElementById("searchInput").value;
    console.log(category);
    console.log(searchInput);
    let price = document.getElementsByClassName("price")[0];
    price.innerText ="";
    if (category == 'fruit') {
        getFruit(searchInput, '/v1/product/fruit/item')
    } else if (category == 'vegetable') {
        getVegetable(searchInput, '/v1/product/vegetable/item')
    }
}
