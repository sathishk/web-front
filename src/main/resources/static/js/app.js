document.addEventListener("DOMContentLoaded", () => {
  const API_URL = "/api/products";
  const productContainer = document.querySelector("#product-list");

  fetch(API_URL)
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then(products => renderProducts(products))
    .catch(error => {
      console.error("Failed to fetch products:", error);
    });

  // 🫖 Render product cards dynamically
  function renderProducts(products) {
    if (!products || products.length === 0) {
      productContainer.innerHTML = `
        <div class="text-center text-muted py-5 w-100">
          No products available at the moment.
        </div>`;
      return;
    }

    const productHTML = products
      .map(
        product => `
        <div class="col-sm-6 col-md-4 col-lg-3">
          <div class="card product-card h-100 shadow-sm">
            <img src="${product.image}" class="card-img-top" alt="${escapeHtml(product.name)}">
            <div class="card-body d-flex flex-column">
              <h5 class="card-title">${escapeHtml(product.name)}</h5>
              <p class="card-text text-muted mb-2">${escapeHtml(product.category)}</p>
              <p class="card-text flex-grow-1">${escapeHtml(shorten(product.description, 80))}</p>
              <div class="d-flex justify-content-between align-items-center mt-3">
                <span class="fw-bold text-primary">$${product.price.toFixed(2)}</span>
                <a href="product-details.html?id=${product.id}" class="btn btn-sm btn-outline-primary">
                  View
                </a>
              </div>
            </div>
          </div>
        </div>
      `
      )
      .join("");

    productContainer.innerHTML = productHTML;
  }

  // ✂️ Helper: shorten long text
  function shorten(text, length) {
    if (!text) return "";
    return text.length > length ? text.substring(0, length).trim() + "..." : text;
  }

  // 🛡️ Helper: escape HTML
  function escapeHtml(unsafe) {
    return unsafe
      ? unsafe
          .replace(/&/g, "&amp;")
          .replace(/</g, "&lt;")
          .replace(/>/g, "&gt;")
          .replace(/"/g, "&quot;")
          .replace(/'/g, "&#039;")
      : "";
  }
});
